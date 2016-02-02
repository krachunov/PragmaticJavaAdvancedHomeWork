package edu.pragmatic.homework.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Debug {

	public static void printInstanceInfo(Object instance)
			throws IllegalArgumentException, IllegalAccessException {
		System.out.println(getInstanceInfo(instance));
	}

	private static String getInstanceInfo(Object instance)
			throws IllegalArgumentException, IllegalAccessException {

		Class<? extends Object> myClass = instance.getClass();

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(printClassInf(myClass));

		stringBuilder.append(PrintObject(myClass));

		stringBuilder.append(printInterfaces(myClass));

		stringBuilder.append(printParentClass(myClass));

		stringBuilder.append(printField(myClass, instance));

		stringBuilder.append(printConstructurs(myClass));

		stringBuilder.append(printMethods(myClass));

		return stringBuilder.toString();

	}

	private static String printClassInf(Class<? extends Object> myClass) {
		StringBuilder stringBuilder = new StringBuilder();
		String name = myClass.getName();
		stringBuilder.append(String.format("Class: %s\n", name));
		return stringBuilder.toString();
	}

	private static String PrintObject(Class<? extends Object> myClass) {
		return String.format("\nObject: %s	\n", myClass.hashCode());
	}

	private static String printParentClass(Class<? extends Object> myClass) {
		StringBuilder stringBuilder = new StringBuilder();
		Class<?> superclass = myClass.getSuperclass();
		String name = superclass.getName();
		stringBuilder.append(String.format("Class: %s\n", name));
		return stringBuilder.toString();
	}

	private static String printInterfaces(Class<? extends Object> myClass) {
		StringBuilder stringBuilder = new StringBuilder();
		Class<?>[] interfaces = myClass.getInterfaces();

		stringBuilder.append(String.format("\nImplements: "));
		for (Class<?> interf : interfaces) {
			String name = interf.getName();
			stringBuilder.append(String.format(" %s", name));
		}
		stringBuilder.append("\n");

		return stringBuilder.toString();
	}

	private static String printField(Class<? extends Object> myClass,
			Object instance) throws IllegalArgumentException,
			IllegalAccessException {

		StringBuilder stringBuilder = new StringBuilder();
		Field[] fields = myClass.getDeclaredFields();

		stringBuilder.append(String.format("\nFields:\n"));
		for (Field field : fields) {
			field.setAccessible(true);

			String modificators = returnModificatorType(field.getModifiers());
			String type = field.getType().getName();
			String name = field.getName();
			Object value = field.get(instance);

			boolean isFieldArray = field.getType().isArray();
			if (!isFieldArray) {
				// is not array
				stringBuilder.append(String.format("%s %s  %s = \"%s\"\n",
						modificators, type, name, value));
			} else {
				// It is array
				int arrayLength = Array.getLength(field.get(instance));
				stringBuilder.append(String.format("%s %s  %s = %s [%d]\n",
						modificators, type, name, value, arrayLength));
				Object[] array = (Object[]) field.get(instance);

				for (int index = 0; index < array.length; index++) {
					Object currentElement = array[index];
					type = currentElement.getClass().getTypeName();
					value = currentElement.toString();
					stringBuilder.append(String.format("\n	[%d] %s (%s)",
							index, type, value));
				}
			}
		}
		return stringBuilder.toString();
	}

	private static String printConstructurs(Class<? extends Object> myClass) {
		StringBuilder stringBuilder = new StringBuilder();
		Constructor<?>[] constructors = myClass.getConstructors();
		stringBuilder.append(String.format("\nConstructors:\n"));

		for (Constructor<?> constructor : constructors) {

			String modificators = returnModificatorType(constructor
					.getModifiers());

			stringBuilder.append(String.format("%s  %s\n", modificators,
					constructor));
		}
		return stringBuilder.toString();
	}

	private static String printMethods(Class<? extends Object> myClass) {
		StringBuilder stringBuilder = new StringBuilder();
		Method[] methods = myClass.getDeclaredMethods();
		stringBuilder.append(String.format("\nMethod:\n"));
		for (Method currentMethod : methods) {

			String modificators = returnModificatorType(currentMethod
					.getModifiers());
			String type = currentMethod.getReturnType().getName().toString();
			String name = currentMethod.getName();
			Class<?>[] throwExceptionTypes = currentMethod.getExceptionTypes();

			if (throwExceptionTypes.length <= 0) {
				stringBuilder.append(String.format("%s %s %s \n", modificators,
						type, name));

			} else {
				stringBuilder.append(String.format("%s %s %s throw ",
						modificators, type, name));
				for (Class<?> exception : throwExceptionTypes) {
					stringBuilder.append(String.format("%S ",
							exception.getName()));
				}
			}
		}
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}

	/**
	 * Public:'+'; Private: '-';Protected: '#';Static:'S';Final'F';
	 * 
	 * @param n
	 *            - number which returns method getModifiers(). Considered
	 *            arguments type numbers int
	 * 
	 * @return - return the symbol: case 1: '+' case 2: '-' case 4: '#' case 8:
	 *         'S' case 9: "[+] [S]" case 10:"[-] [S]" case 16:"[F]" case
	 *         17:"+ F" case 18: "- F";
	 * 
	 */
	private static String returnModificatorType(int n) {
		switch (n) {
		case 1:
			return "[+]";
		case 2:
			return "[-]";
		case 4:
			return "[#]";
		case 8:
			return "[S]";
		case 9:
			return "[+] [S]";
		case 10:
			return "[-] [S]";
		case 16:
			return "[F]";
		case 17:
			return "+ F";
		case 18:
			return "- F";
		default:
			return null;
		}
	}
}
