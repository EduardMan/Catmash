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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    @PostMapping("/addMash")
    public String addPictures(@RequestParam("files") MultipartFile[] files,
                               @ModelAttribute("mashName") String mashName,
                              @ModelAttribute("respondentMessage") String respondentMessage) throws IOException {

        if (files != null) {

            File fileMy = new File(picturesPath);

            if (!fileMy.exists()) {
                fileMy.mkdir();
            }


            Mash mash = new Mash();
            mash.setName(mashName);
            mash.setMessage(respondentMessage);
            mashService.save(mash);

            for (MultipartFile multipartFile :
                    files) {
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + multipartFile.getOriginalFilename();

                multipartFile.transferTo(new File(picturesPath + "/" + resultFileName));

                Target target = new Target();
                target.setMash(mash);
                target.setFileName(resultFileName);
                target.setName(multipartFile.getOriginalFilename().replaceFirst("[.][^.]+$", ""));
                targetService.save(target);
            }

        }

        return "redirect:/";
    }

    @GetMapping("/mash/{mashName}")
    public String mashPage(@PathVariable("mashName") String mashName,
                           Model model,
                           @CookieValue(value = "voted", defaultValue = "") String voted,
                           HttpServletResponse response) {

        Mash mash = mashService.findByName(mashName);
        model.addAttribute("picturesPath", picturesPath);
        model.addAttribute("targets", targetService.getTop10ByRating(mash));
        model.addAttribute("mashName", mashName);
        model.addAttribute("respondentMessage", mash.getMessage());

        Cookie cookie;

        StringBuilder stringBuilder = new StringBuilder(voted);

        List<Long> l = new ArrayList<>();
        if (stringBuilder.length() != 0) {
            for (String str :
                    stringBuilder.toString().split("\\.")) {
                l.add(Long.valueOf(str));
            }
        }

        List<Target> tt = targetService.get2RandomTargets(mashService.findByName(mashName), targetService.findAllByIdIn(l));

        model.addAttribute("voted", true);
        if (tt.size() != 0) {

            model.addAttribute("targetsForMash", tt);
            model.addAttribute("voted", false);
        }

        return "mashPage";
    }

    @PostMapping("/mash/{mashName}/vote")
    @ResponseBody
    public String[] mashVoting(@PathVariable("mashName") String mashName,
                               @ModelAttribute("target") Target target,
                               @ModelAttribute("otherTarget") Target otherTarget,
                               @CookieValue(value = "voted", defaultValue = "") String voted,
                               HttpServletResponse response,
                               Model model) {


        Cookie cookie;

        StringBuilder stringBuilder = new StringBuilder(voted);
        if (!voted.isEmpty())
            stringBuilder.append(".");
        stringBuilder.append(target.getId().toString()).append(".").append(otherTarget.getId().toString());

        cookie = new Cookie("voted", stringBuilder.toString());
        response.addCookie(cookie);

        List<Long> l = new ArrayList<>();
        for (String str :
                stringBuilder.toString().split("\\.")) {
            l.add(Long.valueOf(str));
        }

        List<Target> tt = targetService.get2RandomTargets(mashService.findByName(mashName), targetService.findAllByIdIn(l));

        String[] arr = new String[0];
        if (tt.size() != 0) {
            arr = new String[4];
            arr[0] = tt.get(0).getFileName();
            arr[1] = tt.get(1).getFileName();
            arr[2] = tt.get(0).getId().toString();
            arr[3] = tt.get(1).getId().toString();

            targetService.increaseRating(target);
        }
        return arr;

//        return new String[2];
    }
}
