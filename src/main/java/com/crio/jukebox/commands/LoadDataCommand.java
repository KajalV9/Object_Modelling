package com.crio.jukebox.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import com.crio.jukebox.services.ISongService;

public class LoadDataCommand implements ICommand{

    private final ISongService songService;

    public LoadDataCommand(ISongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        try {
            BufferedReader br = new BufferedReader(new FileReader(tokens.get(1)));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] songContent = line.split(",");
                songService.create(songContent[0],songContent[1],songContent[2],songContent[3],songContent[4].replace("#", ","));
            }
            br.close();
            System.out.println("Songs Loaded successfully");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
    }

    
}
