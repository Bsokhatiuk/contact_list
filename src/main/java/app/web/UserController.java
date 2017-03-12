package app.web;


import app.model.Record;
import app.model.User;
import app.service.SecurityService;
import app.service.UserService;
import app.validator.RecordValidator;
import app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RecordValidator recordValidator;
    private User user;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        user = userForm;
        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/workflow/" + user.getUsername();
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam("username") String username, Model model) {
        user = userService.findByUsername(username);
        return "redirect:/workflow/" + username;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(
            Model model,
            String error,
            String logout) {
        return "welcome";
    }

    @RequestMapping(value = {"/workflow/{name}"}, method = RequestMethod.GET)
    public String workFlow(
            Model model,
            String error,
            String logout) {
        model.addAttribute("newRecordForm", new Record());
        return "workflow";
    }

    @RequestMapping(value = {"/workflow/{name}/{id}"}, method = RequestMethod.GET)
    public String workFlowViewContact(@PathVariable("id") Long id,
                                      @PathVariable("name") String name,
                                      Model model,
                                      String error,
                                      String logout) {
        if (user == null) user = userService.findByUsername(name);
        model.addAttribute("newRecordForm", new Record());
        Record record =userService.findRecord(user,id);
        if (record!=null)
        model.addAttribute("record",record);
        return "workflow";
    }


    @RequestMapping(value = {"/workflow/{name}"}, method = RequestMethod.POST)
    public String addNewRecord(@PathVariable("name") String name,
                               @ModelAttribute("newRecordForm") Record record,
                               BindingResult bindingResult,
                               Model model) {
        if (user == null) user = userService.findByUsername(name);
        recordValidator.validate(record, bindingResult);

        if (!bindingResult.hasErrors()) {
            model.addAttribute("message", "You have been added new contact");
            record.setUser(user);
            userService.saveRecord(record);
        }

        return "workflow";
    }


    @RequestMapping(value = {"/workflow/{name}/{id}"}, method = RequestMethod.POST)
    public String changeRecord(@PathVariable("name") String name,
                               @PathVariable("id") Long id,
                               @ModelAttribute("newRecordForm") Record record,
                               BindingResult bindingResult,
                               Model model) {
        if (user == null) user = userService.findByUsername(name);
        recordValidator.validate(record, bindingResult);

        if (!bindingResult.hasErrors()) {
            Record odlrecord= userService.findRecord(id);
            odlrecord=record;
            model.addAttribute("message", "You have been change your contact");
            odlrecord.setUser(user);
            userService.saveRecord(odlrecord);
        }

        return "workflow";
    }


    @RequestMapping(value = "/contact_list/{name}", method = RequestMethod.GET)
    public String contactList(@PathVariable("name") String name, Model model) {
        if (user == null) user = userService.findByUsername(name);
        List<Record> records = userService.findByUser(user);
        model.addAttribute("records", records);
        return "contact_list";
    }


    @RequestMapping(value = "/contact/{name}", method = RequestMethod.POST)
    public String changeContact(@PathVariable("name") String name, Model model) {

        return "redirect:/contact_list/" + user.getUsername();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable("id") Long id, Model model) {
        Record record = userService.findRecord(id);
        userService.deleteRecord(record);
        return "redirect:/contact_list/" + user.getUsername();

    }
}
