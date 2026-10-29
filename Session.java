    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lostandfoundapp;

public class Session {

    private static int userId;
    private static String name;
    private static String email;
    private static String studentId;
    private static String yearLevel;
    private static String contactNo;
    private static boolean loggedIn = false;

    private Session() {
        // Utility class; do not instantiate.
    }

    public static void setUser(
            int id,
            String name,
            String email,
            String studentId,
            String yearLevel,
            String contactNo) {

        Session.userId = id;
        Session.name = name;
        Session.email = email;
        Session.studentId = studentId;
        Session.yearLevel = yearLevel;
        Session.contactNo = contactNo;
        Session.loggedIn = true;
    }

    public static int getUserId() {
        return userId;
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getStudentId() {
        return studentId;
    }

    public static String getYearLevel() {
        return yearLevel;
    }

    public static String getContactNo() {
        return contactNo;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    // Update session only after the API confirms a successful update.
    public static void updateProfile(
            String newName,
            String newEmail,
            String newStudentId,
            String newYearLevel,
            String newContactNo) {

        if (!loggedIn) {
            throw new IllegalStateException(
                    "Cannot update profile: no active session.");
        }

        name = newName;
        email = newEmail;
        studentId = newStudentId;
        yearLevel = newYearLevel;
        contactNo = newContactNo;
    }

    public static void clear() {
        userId = 0;
        name = null;
        email = null;
        studentId = null;
        yearLevel = null;
        contactNo = null;
        loggedIn = false;
    }
}