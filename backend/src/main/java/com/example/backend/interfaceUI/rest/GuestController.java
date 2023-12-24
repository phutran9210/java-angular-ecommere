package com.example.backend.interfaceUI.rest;

import com.example.backend.application.dto.guestDto.GuestCreateDTO;
import com.example.backend.application.dto.guestDto.GuestDto;
import com.example.backend.application.service.GuestService;
import com.example.backend.domain.service.JwtService;
import com.sun.net.httpserver.Authenticator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api")
public class GuestController {

    private final GuestService guestService;
    private final JwtService jwtService;

    @Autowired
    public GuestController(GuestService guestService,
                           JwtService jwtService) {
        this.guestService = guestService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public void createUser(@RequestBody GuestDto guestDto) {
        System.out.println(guestDto);
//        GuestDto createdGuest = guestService.createGuest(guestDto);
//        return new ResponseEntity<>(createdGuest, HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid GuestCreateDTO guestDto, HttpServletResponse response) {
        try {
            boolean result = this.guestService.authenticateUser(guestDto.getEmail(), guestDto.getPassword());
            if (result) {
                String accessToken = jwtService.createJwtTokenAndSendAsCookie(guestDto.getEmail(), response);
                Map<String, String> bodyResponse = new HashMap<>();
                bodyResponse.put("message", "Login Success");
                bodyResponse.put("accessToken", accessToken);
                return ResponseEntity.status(200).body(bodyResponse);
            }
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Unknown Server Error");
        } catch (Exception e) {
            if (e.getMessage().equals("Login Failed")) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Login Failed");
            }

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server Error : " + e.getMessage());
        }

    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid GuestCreateDTO guestCreateDTO) {
        try {
            GuestDto createdGuest = guestService.createGuest(guestCreateDTO);
            return new ResponseEntity<>(createdGuest, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().equals("Email is Exist")) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Email is Exist");
            }

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unknown Server Error" + e.getMessage());
        }

    }

    @GetMapping(value = "/auth/is-login")
    public boolean isLogin() {
        return true;
    }

    @GetMapping()
    public List<GuestDto> getAll() {
        return guestService.getAllGuest();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> getUserById(@PathVariable String id,
                                                @RequestParam(required = false) Boolean withAddress) {
        Optional<GuestDto> guest = (Boolean.TRUE.equals(withAddress))
                ? guestService.findUserByIdWithAddress(id)
                : guestService.findUserById(id);
        return guest.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
