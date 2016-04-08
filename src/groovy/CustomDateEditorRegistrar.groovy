import org.springframework.beans.PropertyEditorRegistrar
import org.springframework.beans.PropertyEditorRegistry
import org.springframework.beans.propertyeditors.CustomDateEditor
import java.text.SimpleDateFormat

public class CustomDateEditorRegistrar implements PropertyEditorRegistrar {

    public void registerCustomEditors(PropertyEditorRegistry registry) {

        String dateFormat = "yyyy-MM-dd"
        registry.registerCustomEditor(Date, new CustomDateEditor(new SimpleDateFormat(dateFormat), true))
    }
}
