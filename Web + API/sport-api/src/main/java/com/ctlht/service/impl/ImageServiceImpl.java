package com.ctlht.service.impl;

import com.ctlht.api.ResourceSerializable;
import com.ctlht.service.ImageService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public ResourceSerializable getFile(String imageName) {
        try {
            File file = null;
            file = new File("C:\\Users\\VTD\\Pictures\\image\\"+ File.separator + imageName);
            return new ResourceSerializable(file.toURI());
        }
        catch (IOException e){

        }
        return null;
    }
}
