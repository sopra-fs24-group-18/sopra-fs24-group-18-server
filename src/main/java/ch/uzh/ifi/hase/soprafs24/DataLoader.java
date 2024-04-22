package ch.uzh.ifi.hase.soprafs24;

import ch.uzh.ifi.hase.soprafs24.constant.ToolType;
import ch.uzh.ifi.hase.soprafs24.repository.ToolRepository;
import ch.uzh.ifi.hase.soprafs24.entity.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private ToolRepository toolRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Tool tool1 = new Tool();
        tool1.setType(ToolType.HINT);
        tool1.setDescription("Using this tool, you can get some hints in next round.");
        tool1.setPrice(30L);
        toolRepository.save(tool1);

        Tool tool2 = new Tool();
        tool2.setType(ToolType.BLUR);
        tool2.setDescription("Using this tool, you can blur others' images in next round.");
        tool2.setPrice(30L);
        toolRepository.save(tool2);
    }
}