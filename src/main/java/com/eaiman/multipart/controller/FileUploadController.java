package com.eaiman.multipart.controller;

import com.eaiman.multipart.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("multipart-file")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)

//    public Mono<List<String>> upload(@RequestPart("file") FilePart filePartFlux) {
//    public Mono<List<String>> upload(@RequestPart("file") Mono<FilePart> filePartFlux) {
//    public Mono<List<String>> upload(@RequestPart("file") Mono<MultiValueMap<String, Part> filePartFlux) {
    public Mono<List<String>> upload(@RequestPart("file") Flux<FilePart> filePartFlux) {

        /*
          To see the response beautifully we are returning strings as Mono List
          of String. We could have returned Flux<String> from here.
          If you are curious enough then just return Flux<String> from here and
          see the response on Postman
         */
        return fileUploadService.getLines(filePartFlux).collectList();
    }
}