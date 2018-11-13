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

  //FIXME implement constructors if needed
  
  /**
   * @param datafile
   * @throws ImportFileException
   * @throws InvalidCourseSelectionException
   */
  public void importFile(String datafile) throws ImportFileException {
    try {
      _school.importFile(datafile);
    } catch (IOException | BadEntryException e) {
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
  }

  /**
   * @return true when the currently logged in person is an administrative
   */
  public boolean isLoggedUserAdministrative() {
    //FIXME implement predicate
  }

  /**
   * @return true when the currently logged in person is a professor
   */
  public boolean isLoggedUserProfessor() {
    //FIXME implement predicate
  }

  /**
   * @return true when the currently logged in person is a student
   */
  public boolean isLoggedUserStudent() {
    //FIXME implement predicate
  }

  /**
   * @return true when the currently logged in person is a representative
   */
  public boolean isLoggedUserRepresentative() {
    //FIXME implement predicate
  }
  
  //FIXME implement other methods (in general, one for each command in sth-app)
  
  public void doChangePhoneNumber(Person person, String newPhoneNumber) {
	  person.changePhoneNumber(newPhoneNumber);
  }
  
  public Collection<String> doSearchPerson(String str) {
	  Collection<Person> peopleList = _school.getAllUsers(str);
	  ArrayList<String> strList = new ArrayList<>();
	  for (Person p: peopleList)
		  strList.add(p.toString());
	  return strList;
  }
  
  public String getHeader(Person person) {
	  return person.getType() + "|" + person.getId() + "|" + 
      person.getPhoneNumber() + "|" + person.getName();
  }
  
  
}
