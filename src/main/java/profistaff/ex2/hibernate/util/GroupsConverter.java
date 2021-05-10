package profistaff.ex2.hibernate.util;

import profistaff.ex2.hibernate.enums.Groups;
import static profistaff.ex2.hibernate.enums.Groups.COMPUTER_SCIENCE;
import static profistaff.ex2.hibernate.enums.Groups.ECONOMY;
import static profistaff.ex2.hibernate.enums.Groups.JURISPRUDENCE;
import static profistaff.ex2.hibernate.enums.Groups.MANAGEMENT;

/**
 * This is static class that convert {@link Groups} representation of student group name in {@link String}
 * representation and vice versa
 * @see     GroupsConverter#groupToString(Groups)
 * @see     GroupsConverter#stringToGroup(String)
 * @author  Kambachekov Murat
 * @version 1.0
 */
public class GroupsConverter {
	
	/**
	 * Convert {@link String} format in {@link Groups} format
	 * @param  group - {@link String} format representation of student group name
	 * @return {@link Groups} format representation of student group name
	 */
	public static Groups stringToGroup(String group) {
		switch (group) {
			case "Экономика": return ECONOMY;
			case "Информатика": return COMPUTER_SCIENCE;
			case "Юриспруденция": return JURISPRUDENCE;
			case "Менеджмент": return MANAGEMENT;
		}
		return null;
	}
	/**
	 * Convert {@link Groups} format in {@link String} format
	 * @param  group - {@link Groups} format representation of student group name
	 * @return {@link String} format representation of student group name
	 */
	public static String groupToString(Groups group) {
		switch (group) {
			case ECONOMY: return "Экономика";
			case COMPUTER_SCIENCE: return "Информатика";
			case JURISPRUDENCE: return "Юриспруденция";
			case MANAGEMENT: return "Менеджмент";
		}
		return null;
	}
}
