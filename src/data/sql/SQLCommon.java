package data.sql;

public final class SQLCommon {
	static public class Person {
		static public final String ID = "id";
		static public final String RANK = "rank";
		static public final String FIRST_NAME = "first_name";
		static public final String MIDDLE_NAME = "middle_name";
		static public final String LAST_NAME = "last_name";
		
		static public class Rank {
			static public final String STUDENT = "STUDENT";
			static public final String FACILITY = "FACILITY";
			static public final String STAFF = "STAFF";
		}
	}
	
	static public class Term {
		static public final String ID = "id";
		static public final String TERM = "term";
	};
	
	static public class Class {
		static public final String ID = "id";
		static public final String NAME = "name";
		static public final String ROOM_NUMBER = "room_number";
		static public final String PERIOD = "period";
		static public final String TERM = "term";
	}
}
