package org.ehrbase.validation.webtemplate;

import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import java.util.ArrayList;
import java.util.List;
import org.ehrbase.validation.ConstraintViolation;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * {@link ConstraintValidator} that validates a <code>DV_QUANTITY</code> object.
 *
 * @see com.nedap.archie.rm.datavalues.quantity.DvQuantity
 * @since 1.7
 */
@SuppressWarnings("unused")
public class DvQuantityValidator implements ConstraintValidator<DvQuantity> {

  private final PrimitiveConstraintMapper mapper = new PrimitiveConstraintMapper();

  private final PrimitiveConstraintValidator validator = new PrimitiveConstraintValidator();

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<DvQuantity> getAssociatedClass() {
    return DvQuantity.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ConstraintViolation> validate(DvQuantity quantity, WebTemplateNode node) {
    List<ConstraintViolation> result = new ArrayList<>();

    WebTemplateValidationUtils.findInputWithSuffix(node, "magnitude")
        .ifPresent(input -> result.addAll(validateMagnitude(node.getAqlPath(), quantity, input)));

    WebTemplateValidationUtils.findInputWithSuffix(node, "unit")
        .ifPresent(input -> result.addAll(validateUnit(node.getAqlPath(), quantity, input)));

    return result;
  }

  private List<ConstraintViolation> validateMagnitude(String path, DvQuantity quantity,
      WebTemplateInput input) {
    return validator.validate(path, quantity.getMagnitude(), input);
  }

  private List<ConstraintViolation> validateUnit(String path, DvQuantity quantity,
      WebTemplateInput unitInput) {
    var cString = mapper.mapTextInput(unitInput);
    var result = validator.validate(path, quantity.getUnits(), cString);

    if (result.isEmpty()) {
      WebTemplateValidationUtils.findInputValue(unitInput, quantity.getUnits())
          .ifPresent(unitValue -> {
            if (WebTemplateValidationUtils.hasValidationRange(unitValue)) {
              var cReal = mapper.mapRealInterval(unitValue.getValidation().getRange());
              result.addAll(validator.validate(path, quantity.getMagnitude(), cReal));
            }

            if (WebTemplateValidationUtils.hasValidationPrecision(unitValue)
                && quantity.getPrecision() != null) {
              var cInteger = mapper.mapIntegerInterval(unitValue.getValidation().getPrecision());
              result.addAll(validator.validate(path, quantity.getPrecision(), cInteger));
            }
          });
    }

    return result;
  }
}
