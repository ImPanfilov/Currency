package com.impanfilov.alpha.client;

import com.impanfilov.alpha.dto.Giphy;
import org.springframework.stereotype.Component;

@Component
public class GiphyClientFallback implements GiphyClient{

    @Override
    public Giphy getGif(String string){
        return null;
    }

}
