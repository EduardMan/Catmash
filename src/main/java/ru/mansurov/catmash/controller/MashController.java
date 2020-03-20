package ru.mansurov.catmash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mansurov.catmash.model.Mash;
import ru.mansurov.catmash.model.Target;
import ru.mansurov.catmash.model.service.MashServiceImpl;
import ru.mansurov.catmash.model.service.TargetServiceImpl;
import ru.mansurov.catmash.util.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class MashController {

    @Autowired
    MashServiceImpl mashService;

    @Autowired
    TargetServiceImpl targetService;

    @Value("${pictures.path}")
    private String picturesPath;

    @Value("${pictures.min.count}")
    private int picturesMinCount;

    @Value("${pictures.max.count}")
    private int picturesMaxCount;

    @Value("${mash.min.name.length}")
    private int minMashNameLength;

    @Value("${mash.max.name.length}")
    private int maxMashNameLength;

    @Value("${mash.min.message.length}")
    private int minMashMessage;

    @Value("${mash.max.message.length}")
    private int maxMashMessage;

    @PostMapping("/addMash")
    public String addPictures(@RequestParam("files") MultipartFile[] files,
                              @ModelAttribute("mashName") String mashName,
                              @ModelAttribute("respondentMessage") String respondentMessage) throws IOException {

        if (files != null && files.length >= picturesMinCount && files.length <= picturesMaxCount && files.length % 2 == 0
                && !mashName.isEmpty() && mashName.length() >= minMashNameLength && mashName.length() <= maxMashNameLength
                && !respondentMessage.isEmpty() && respondentMessage.length() >= minMashMessage && respondentMessage.length() <= maxMashMessage) {

            boolean folderExistence = Utils.checkFolderExistence(picturesPath);

            if (folderExistence) {
                // Create new mash  and save in DB
                Mash mash = new Mash();
                mash.setName(mashName);
                mash.setMessage(respondentMessage);
                mashService.save(mash);

                // Copy files to server
                for (MultipartFile multipartFile :
                        files) {
                    // UUID prefix for file name
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFileName = uuidFile + "." + multipartFile.getOriginalFilename();

                    // Copy file to server
                    multipartFile.transferTo(new File(picturesPath + "/" + resultFileName));

                    // Create new target and save in DB
                    Target target = new Target();
                    target.setMash(mash);
                    target.setFileName(resultFileName);
                    // Name for picture, it based on file name without extension
                    target.setName(Utils.getFileNameWithoutExtension(multipartFile.getOriginalFilename()));
                    targetService.save(target);
                }
            }

        }

        return "redirect:/";
    }

    @GetMapping("/mash/{mashName}")
    public String mashPage(@PathVariable("mashName") String mashName,
                           Model model,
                           @CookieValue(value = "voted", defaultValue = "") String voted) {

        Mash currentMash = mashService.findByName(mashName);
        List<Target> newRandomTargets = targetService.get2RandomTargets(currentMash,
                targetService.findAllByIdIn(Utils.getIdsFromCookieString(voted)));

        model.addAttribute("mash", currentMash);

        // newRandomTargets.size() == 0 is true means that we do not have new targets for person
        // consequently he has already voting so we just show we will show him the results
        // newRandomTargets.size() == 0 is false means that person still does not finished the mesh
        // so we wll give him new targets
        if (newRandomTargets.size() == 0) {
            model.addAttribute("topTargets", targetService.getTop10ByRating(currentMash));
            model.addAttribute("voted", true);
        } else {
            model.addAttribute("targetsForMash", newRandomTargets);
            model.addAttribute("voted", false);
        }

        return "mashPage";
    }

    @PostMapping("/mash/{mashName}/vote")
    @ResponseBody
    public List<Target> mashVoting(@PathVariable("mashName") String mashName,
                                   @ModelAttribute("target") Target target,
                                   @ModelAttribute("otherTarget") Target otherTarget,
                                   @CookieValue(value = "voted", defaultValue = "") String voted,
                                   HttpServletResponse response) {


        List<Target> newRandomTargets = null;
        if (mashName != null && !mashName.isEmpty() && target != null && otherTarget != null) {

            // Get two relative random targets, excluding targets which person has already voted plus current targets
            String idsFilter = voted + (voted.length() == 0 ? "" : ".") + target.getId() + "." + otherTarget.getId();
            newRandomTargets = targetService.get2RandomTargets(mashService.findByName(mashName),
                    targetService.findAllByIdIn(Utils.getIdsFromCookieString(idsFilter)));

            // Increase ration of selected target and write to cookie new shown targets
            targetService.increaseRating(target);
            response.addCookie(new Cookie("voted", idsFilter));

        }
        return newRandomTargets;
    }
}
