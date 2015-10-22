package service.representation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRepresentationValidator
{

	private static Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	private static Pattern phonePattern = Pattern.compile("[0-9]{12}");
	private static Matcher matcher;

	public static boolean validate(UserRepresentation userRepresentation)
	{
		if(userRepresentation.getFirstName().length()>255)
			return false;
		if(userRepresentation.getLastName().length()> 255)
			return false;
		if(userRepresentation.getMiddleName().length()>255)
			return false;
		if(userRepresentation.getEmail().length()>255)
			return false;

		if(userRepresentation.getPassword().length()>50)
			return false;

		matcher = emailPattern.matcher(userRepresentation.getEmail());
		if(!matcher.matches())
			return false;

		matcher = phonePattern.matcher(userRepresentation.getPhoneNumber());
		return matcher.matches();

	}
}
