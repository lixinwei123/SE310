PART C MATERIAL:
Created by Kevin Li(kl958) on November, 2019 for SE310, hw2 part C
*Note- Two example sample files for each survey and test are created already for you to modify, take, and tabulate to see the results.
Where the sample test files are located:
   It is stored in the same location like part B, no more folders are added.
Features update:
Modification-Enables the user to modify survey and test: please run the program to see all the functionality of this function for each
types of questions. Though generally, for multiple choice, it allows to add, delete, modify choices and its prompt. Matching is a little
bit different which allows editing left and right column but the idea is the same. Finally, essay and short answer's difference where
short answers allows you to have correct answers and you can add more correct answers, modify existing one, or delete existing one.

taking a test/survey-Very straight forward taking system where user enters A/B/C/D.. for multiple choice and emoji. Selecting from index
for matching, enter manually for short answer, and T/F for true and false.

tabulate a survey/test-Tabulate all of the results like the examples shown in the pdf.

saving test result notes-After the user takes a test, the results are reserialized back into the same survey/test inside a hashmap.
Creating new files to store results seemed unreasonable and I did not plan to implement RCA for this. However, all initial functionality
is satisfied since it states that I just need to serialize the result into a file and did not mention if I need to create a new separate
files for it.

test result-It is marked by id counter. Every time a new user takes the test, the id is incremented by 1, thus if you choose "grade test",
you can see the total test results of a given test.
----------------------------------------------------------------------------------------------------------------------
PART B MATERIAL:
----------------------------------------------------------------------------------------------------------------------
Created by Kevin Li(kl958) on November, 2019 for SE310, hw2 part B
----------------------------------------------------------------------------------------------------------------------
Where the sample test files are located:
   1.The sample test files are located under the "Serializable" folder, surveys and tests are stored in
   separate folders.
   2.I decided to use extension type .ser, stands for serializable.
   3.As indicated, I used serialization to save and load the file.
----------------------------------------------------------------------------------------------------------------------
Description of the program:
This program allows you to create a survey or test of different question types. Where later on, you can display the
surveys and tests that you have created and modify the survey/tests that you have created.
----------------------------------------------------------------------------------------------------------------------
Notes about user inputs:
1.The inputs do not accept blank, spaces.
2.Some part of the program requires user to confirm with a "Y", the user can cancel by typing anything else besides blank.
----------------------------------------------------------------------------------------------------------------------
How the program works, from a user's perspective:
*Note, only one survey may be active at a given time, so if the user load a survey or create a new survey,
the current survey will be overwritten if the user gives permission.

1. An initial menu that asks user to choose survey, test, or exit
2. Survey menu and test menu has the same content, the following explanation assumes "survey" scenarios:
    a).Create a new survey- will create a new survey, unless the user already had created a survey, which
    the program will prompt and suggest to the user to save the current survey first, or discard current one
    and start a new survey, or continue the current survey.

    b).Display a survey- will display all the surveys in the survey folder along with the current survey that the
    user is working on(if exists). Then prompts the user to select from the list to display the selected survey.

    c).Load a survey-will display all the surveys in the survey folder, then prompts the user to select the survey
    the user wants to load. If there is a current survey, will prompt the user for permission to overwrite the current
    survey.

    d).Save current survey-will save the current survey instance inside the survey folder, prompting the user for a name,
    if a file with the same name already existed, will prompt the user for a different name.

    e).Continue current survey-allows user to continue the current survey instance where the user can add more questions
    to this instance. This can be triggered if the user created a survey, or loaded a survey.

    f).Back-goes back to the first menu. If there exists an unsaved survey object, the user will be prompted to save it
    before going back.

    c).Quit-exit the program.
3. Create a new test menu has different types of question that you can add, the following explanation is based on "test":
    a).Add a new T/F question- straight forward

    b).Add a new multiple-choice question-You have to select from the list of answers for correct answers

    c).Add a new short answer-straight forward

    d).Add a new essay-straight forward

    e).add a new emoji-There is no correct answer for both survey and test

    6).add a matching- same logic as add a multiple choice for correct answer
----------------------------------------------------------------------------------------------------------------------
How the program works, from a developer's perspective:
    a).The main class calls Menu1 class, which goes to either SurveyMenu or TestMenu class. For menus, if user enter
    back for the menus, the function that continues the menu loop will return. For Menu 3 that adds different types of
    questions, when user press back, a survey or test object instance is returned. Then, currentSurvey is set to this
    instance.
    b).serialization is used properly in In and Out classes. I




