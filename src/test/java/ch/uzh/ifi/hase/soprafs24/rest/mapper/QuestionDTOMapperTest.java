package ch.uzh.ifi.hase.soprafs24.rest.mapper;

import ch.uzh.ifi.hase.soprafs24.constant.GameMode;
import ch.uzh.ifi.hase.soprafs24.entity.Question;
import ch.uzh.ifi.hase.soprafs24.rest.dto.question.QuestionGetDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class QuestionDTOMapperTest {

    @Test
    public void testConvertEntitytoQuestionGetDTO_Guessing() {
        // Create a sample Question entity
        Question question = new Question();
        question.setId(1L);
        question.setRoomId(123L);
        question.setGameMode(GameMode.GUESSING);
        question.setRoundNumber(1);
        question.setItemId(456L);
        question.setItemImage("image1.jpg");
        question.setLeftRange(0);
        question.setRightRange(100);
        question.setOriginLeftRange(0);
        question.setOriginRightRange(80);
        // Convert entity to DTO using the mapper
        QuestionGetDTO questionGetDTO = QuestionDTOMapper.INSTANCE.convertEntitytoQuestionGetDTO(question);

        // Check if the conversion is successful
        assertEquals(question.getId(), questionGetDTO.getId());
        assertEquals(question.getRoomId(), questionGetDTO.getRoomId());
        assertEquals(question.getGameMode(), questionGetDTO.getGameMode());
        assertEquals(question.getRoundNumber(), questionGetDTO.getRoundNumber());
        assertEquals(question.getItemId(), questionGetDTO.getItemId());
        assertEquals(question.getItemImage(), questionGetDTO.getItemImage());
        assertEquals(question.getLeftRange(), questionGetDTO.getLeftRange());
        assertEquals(question.getRightRange(), questionGetDTO.getRightRange());
        assertEquals(question.getOriginLeftRange(), questionGetDTO.getOriginLeftRange());
        assertEquals(question.getOriginRightRange(), questionGetDTO.getOriginRightRange());
        assertEquals(question.getBlur(), questionGetDTO.getBlur());
    }

    @Test
    public void testConvertEntitytoQuestionGetDTO_Budget() {
        // Create a sample Question entity
        Question question = new Question();
        question.setId(1L);
        question.setRoomId(123L);
        question.setGameMode(GameMode.BUDGET);
        question.setRoundNumber(1);
        question.setItemList("item1,item2");
        question.setItemImageList("image1.jpg,image2.jpg");
        question.setBudget(123);
        question.setSelectedItemList("item1");
        question.setSelectedItemNum(1);

        // Convert entity to DTO using the mapper
        QuestionGetDTO questionGetDTO = QuestionDTOMapper.INSTANCE.convertEntitytoQuestionGetDTO(question);

        // Check if the conversion is successful
        assertEquals(question.getId(), questionGetDTO.getId());
        assertEquals(question.getRoomId(), questionGetDTO.getRoomId());
        assertEquals(question.getGameMode(), questionGetDTO.getGameMode());
        assertEquals(question.getRoundNumber(), questionGetDTO.getRoundNumber());
        assertEquals(question.getItemList(), questionGetDTO.getItemList());
        assertEquals(question.getItemImageList(), questionGetDTO.getItemImageList());
        assertEquals(question.getBlur(), questionGetDTO.getBlur());
        assertEquals(question.getBudget(), questionGetDTO.getBudget());
        assertEquals(question.getSelectedItemList(), questionGetDTO.getSelectedItemList());
        assertEquals(question.getSelectedItemNum(), questionGetDTO.getSelectedItemNum());
    }

}