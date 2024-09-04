package com.example.CleanDar.Controller;


import com.example.CleanDar.Dto.PackDto;
import com.example.CleanDar.Service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/packs")
public class PackController {

    @Autowired
    private PackService packService;

    @PostMapping("/add")
    public ResponseEntity<PackDto> addPack(@RequestBody PackDto packDTO) {
        PackDto createdPack = packService.createPack(packDTO);
        return ResponseEntity.ok(createdPack);
    }
}