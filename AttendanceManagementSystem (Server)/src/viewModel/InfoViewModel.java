package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Date;
import model.Model;

import java.time.LocalDate;

public class InfoViewModel
{
  private StringProperty subject;
  private StringProperty topic;
  private StringProperty contents;
  private StringProperty homework;
  private StringProperty teacher;
  private ObjectProperty<LocalDate> date;
  private StringProperty error;
  private StringProperty classId;
  private StringProperty absent;

  private Model model;

  public InfoViewModel(Model model)
  {
    this.model = model;

    this.subject = new SimpleStringProperty();
    this.topic = new SimpleStringProperty();
    this.contents = new SimpleStringProperty();
    this.homework = new SimpleStringProperty();
    this.teacher = new SimpleStringProperty();
    this.date = new SimpleObjectProperty<>();
    this.error = new SimpleStringProperty();
    this.classId = new SimpleStringProperty();
    this.absent = new SimpleStringProperty();
  }

  public StringProperty getSubjectProperty()
  {
    return subject;
  }

  public StringProperty getTopicProperty()
  {
    return topic;
  }

  public StringProperty getContentsProperty()
  {
    return contents;
  }

  public StringProperty getHomeworkProperty()
  {
    return homework;
  }

  public StringProperty getTeacherProperty()
  {
    return teacher;
  }

  public ObjectProperty<LocalDate> getDateProperty()
  {
    return date;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

  public StringProperty getClassIdProperty()
  {
    return classId;
  }

  public StringProperty getAbsentProperty()
  {
    return absent;
  }

  public void clear()
  {
  }

  // TODO
}
