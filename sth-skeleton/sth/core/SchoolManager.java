package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;


//FIXME import other classes if needed

/**
 * The façade class.
 */
public class SchoolManager {

  //FIXME add object attributes if needed

  private School _school;

  private Person _loggedUser;

  //FIXME implement constructors if needed
  
  /**
   * @param datafile
   * @throws ImportFileException
   * @throws InvalidCourseSelectionException
   */
  public void importFile(String datafile) throws ImportFileException {
    try {
      _school.importFile(datafile);
    } 
    catch (IOException | BadEntryException e) {
      throw new ImportFileException(e);
    }
  }

  /**
   * Do the login of the user with the given identifier.

   * @param id identifier of the user to login
   * @throws NoSuchPersonIdException if there is no uers with the given identifier
   */
  public void login(int id) throws NoSuchPersonIdException {
    //FIXME implement method
      _loggedUser = _school.getPerson(id)
  }

  /**
   * @return true when the currently logged in person is an administrative
   */
  public boolean isLoggedUserAdministrative() {
    return _loggedUser.getPersonType() == "FUNCIONÁRIO";
  }

  /**
   * @return true when the currently logged in person is a teacher
   */
  public boolean isLoggedUserTeacher() {
    return _loggedUser.getPersonType() == "DOCENTE";
  }

  /**
   * @return true when the currently logged in person is a student
   */
  public boolean isLoggedUserStudent() {
    return _loggedUser.getPersonType() == "ALUNO";
  }

  /**
   * @return true when the currently logged in person is a representative
   */
  public boolean isLoggedUserRepresentative() {
    return _loggedUser.getPersonType() == "DELEGADO";
  }
  
  //FIXME implement other methods (in general, one for each command in sth-app)
  
  public void changePhoneNumber(Person person, String newPhoneNumber) {
	  person.changePhoneNumber(newPhoneNumber);
  }
  
  public List<String> doSearchPerson(String str) {
	  List<Person> peopleList = _school.getAllUsers(str);
	  List<String> strList = new ArrayList<>();
	  for (Person p: peopleList)
		  strList.add(p.toString());
	  return strList;
  }
}
