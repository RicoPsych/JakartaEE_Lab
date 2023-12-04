package lab.song.validation.binding;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lab.song.validation.validator.SongValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Binding annotation for BeansValidation custom validator. Checks if sum of character's statistics (strength,
 * constitution and charisma) is equal or lower than 54.
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SongValidator.class)
@Documented
public @interface ValidSong {

    /**
     * @return error message
     */
    String message() default "${validatedValue.rating} should be between {limitLower} and {limitUpper}";

    /**
     * @return validation groups
     */
    Class<?>[] groups() default {};

    /**
     * @return additional payload for programmer, not used by BeansValidation
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * @return limit for sum of statistics
     */
    int limitUpper() default 5;

    int limitLower() default 0;

}
