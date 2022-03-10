package com.cvrs.backend.controller.base;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://192.168.1.2:3000",
        "http://192.168.1.74:3000",
        "http://192.168.1.68:3000",
        "http://192.168.0.136:3000",
        "http://192.168.1.176:3000"
})
public class BaseController {

}
