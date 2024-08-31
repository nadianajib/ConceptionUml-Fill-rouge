package com.example.demo3.Controller;

import com.example.demo3.Dto.PackDto;
import com.example.demo3.Service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/packs")
public class PackController {

    @Autowired
    private PackService packService;

    @PostMapping
    public ResponseEntity<PackDto> addPack(@RequestBody PackDto packDTO) {
        PackDto createdPack = packService.createPack(packDTO);
        return ResponseEntity.ok(createdPack);
    }
}
