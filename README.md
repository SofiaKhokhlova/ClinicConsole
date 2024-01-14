# ClinicConsole

Streamlining Clinic Operations with a User-Friendly Console App.

- The class "Clinic" contains lists of Patients and Medical Staff (Doctors and Nurses).
- The class "Clinical System" has fields for a user and an index to identify the user in the system. It has a static
  method to start the system, separate methods for user search in the system, inputting data to change personal
  information, and checking the validity of input data during registration.
- The abstract class "Clinical User" contains fields for personal data and has descendants - Clinical Staff and
  Patients.
- The abstract class "Clinical Staff" has a position characteristic and also has descendants - Doctors and Nurses.

- The class "Nurse" has a list of analyses that need processing. A nurse can view and process analyses from her list,
  change personal information, and remove herself from the system (the list of medical staff).
- The class "Doctor" contains an array of Appointments. A doctor can update Appointments for today (complete the
  appointment, creating a new conclusion for the patient and entering data about the date, medical conclusion; it is
  also possible to create a referral for analysis if needed), view all appointments for today, cancel all appointments
  for today, change personal information, and remove himself from the system (the list of medical staff).
- The class "Patient" has a list of medical conclusions (essentially completed Appointments). A patient can view the
  history of their medical conclusions, schedule an appointment with a specific doctor, cancel an appointment, change
  personal information, and remove themselves from the system (the list of patients).

- The class "Appointment" contains information about the doctor, patient, and appointment time.
- The class "Medical Conclusion" contains information about the doctor, patient, date, conclusion, treatment, and a list
  of analyses (if prescribed by the doctor).
- The class "Medical Analysis" contains information about the patient, type of analysis, status, name, date, and result.
  The nurse responsible for processing the analysis is determined by the type of analysis.

- In the enum "Analysis Type," three types of analysis are defined: blood, urine, and swab.
- In the enum "Analysis Status," two statuses are defined: "referral created" and "completed."

In the Main file, upon the initial execution, it checks for the presence of a file to store data. If the file is absent,
it is created from scratch with a specified name in the current directory. In any case, the ClinicSystem method is
executed - start.