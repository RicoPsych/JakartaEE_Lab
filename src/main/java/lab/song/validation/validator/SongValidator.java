package lab.song.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lab.song.domain.Rating;
import lab.song.entities.Song;
import lab.song.model.SongCreateModel;
import lab.song.validation.binding.ValidSong;

/**
 * Characters stats validator. Checks if sum of character basic stats is lower or equal than provided value.
 */
public class SongValidator implements ConstraintValidator<ValidSong, Rating> {

    /**
     * Upper limit value for character basic stats values sum.
     */
    private int limitLower;
    private int limitUpper;

    @Override
    public void initialize(ValidSong constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        limitLower = constraintAnnotation.limitLower();
        limitUpper = constraintAnnotation.limitUpper();
    }

    @Override
    public boolean isValid(Rating value, ConstraintValidatorContext context) {
        return  limitLower <=  (value.getRating()) && (value.getRating()) <= limitUpper;
    }
}
