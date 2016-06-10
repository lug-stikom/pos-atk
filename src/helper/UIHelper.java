package helper;

import java.awt.Component;

public final class UIHelper {
	public static void bottomOf(Component subject, Component object, int margin){
		object.setLocation(subject.getX(), subject.getY() + subject.getHeight() + margin);
	}

	public static void bottomOf(Component subject, Component object){
		bottomOf(subject, object, 0);
	}

	public static void topOf(Component subject, Component object, int margin){
		object.setLocation(subject.getX(), subject.getY() - object.getHeight() - margin);
	}

	public static void topOf(Component subject, Component object){
		topOf(subject, object, 0);
	}

	public static void rightOf(Component subject, Component object, int margin){
		object.setLocation(subject.getX() + subject.getWidth() + margin, subject.getY());
	}

	public static void rightOf(Component subject, Component object){
		rightOf(subject, object, 0);
	}

	public static void leftOf(Component subject, Component object, int margin){
		object.setLocation(subject.getX() - object.getWidth() - margin, subject.getY());
	}

	public static void leftOf(Component subject, Component object){
		leftOf(subject, object, 0);
	}
}
