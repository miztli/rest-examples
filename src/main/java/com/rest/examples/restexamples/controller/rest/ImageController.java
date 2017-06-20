package com.rest.examples.restexamples.controller.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by miztli on 20/06/17.
 */
@RestController
@RequestMapping(value = "/user/image")
public class ImageController {

    public static final String PATH = "/home/miztli/Workspace/personal-projects/rest-examples/";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadImage(@RequestParam("file") MultipartFile file) {
        System.out.println("-----Uploading image...-----");
        System.out.println("Image getSize: " + file.getSize());
        System.out.println("Image getName: " + file.getName());
        System.out.println("Image getContentType: " + file.getContentType());
        System.out.println("Image getOriginalFilename: " + file.getOriginalFilename());
        System.out.println("Image class name: " + file.getClass().getSimpleName());

        String routePath;
        switch(file.getContentType()){
            case(MediaType.IMAGE_JPEG_VALUE):
                routePath = PATH + "image.jpeg";
                break;
            case(MediaType.IMAGE_PNG_VALUE):
                routePath = PATH + "image.png";
                break;
            default:
                throw new RuntimeException("Invalid Media Type");
        }

        try{
            System.out.println("-----Saving file------");
            System.out.println(routePath);
            Files.write(new File(routePath).toPath(), file.getBytes());
        }catch (IOException e){
            System.out.println("Couldn't create image file: " + e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<byte[]> getImage(@RequestParam("name") String name){

        try{
            byte[] fileBytes = Files.readAllBytes(Paths.get(PATH + name));
            if(name.contains(".jpeg")) {
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .contentLength(fileBytes.length)
                        .body(fileBytes);
            }else if(name.contains(".png")){
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .contentLength(fileBytes.length)
                        .body(fileBytes);
            }else{
                throw new RuntimeException("File not found");
            }
        }catch (IOException e){
            System.out.println("Couldn't read file: " + PATH + name);
            throw new RuntimeException("File not found");
        }
    }
}
