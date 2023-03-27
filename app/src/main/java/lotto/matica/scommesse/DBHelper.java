package lotto.matica.scommesse;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public final static int DATABASE_VERSION=1;
    public final static String DATABASE_NAME="my_db";
    public final static String TABLE_NAME="exercises";


    public final static String KEY_ID="_id";
    public final static String KEY_MUSCLE_ID="muscle_id";
    public final static String KEY_NAME="name";
    public final static String KEY_DESCRIPTION="description";
    public final static String KEY_PHOTO="photo";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String[] name_exer= {"Barbell Overhead Press","Dumbbell Seated Overhead \nPress",
                "Cable Lateral Raise", "Barbell Upright Row","Dumbbell Seated Shrug","Dumbbell Shrug","Barbell Silverback Shrug","Barbell Squat","Forward Lunges",
                "Machine Leg Extension","Kettlebell Step Up","Barbell Stiff Leg Deadlifts","Machine Hamstring Curl","Machine Standing Calf\nRaises",
                "Barbell Calf Raises","Kettlebell Single Leg Calf Raise","Barbell Bench Press","Push Up","Dumbbell Incline\nBench Press",
                "Dumbbell Incline\nChest Flys","Cable Push Down","Barbell Close Grip\nBench Press","Dumbbell Skullcrusher","Bench Dips","Cable Pallof Press","Hand Side Plank",
                "Dumbbell Wood Chopper","Barbell Landmine\nSide Bend","Barbell Curl","Chin Ups","Dumbbell Curl","Dumbbell Hammer\nCurl ",
                "Pull Ups","Barbell Deadlift","Dumbbell Row\nUnilateral","Kettlebell Incline\nShrug","Barbell Deadlift","Machine 45 Degree Back Extension",
                "Supermans","Barbell Bent\nOver Row","Machine Pulldown"};
        String[] photo= {"https://149874912.v2.pressablecdn.com/wp-content/uploads/2020/12/Overhead-press-exercise.gif",
                "https://thumbs.gfycat.com/ExcitableOblongFluke-max-1mb.gif",
                "https://fitnessprogramer.com/wp-content/uploads/2021/02/Cable-Lateral-Raise.gif",
                "https://blog.squatwolf.com/wp-content/uploads/2021/07/2-2.gif",
                "https://thumbs.gfycat.com/ChubbyHotDeer-size_restricted.gif",
                "https://media.tenor.com/uMNZPBaaTPYAAAAC/dumbbell-shrug.gif",
                "https://thumbs.gfycat.com/DependableMediumAlbacoretuna-max-1mb.gif",
                "https://149874912.v2.pressablecdn.com/wp-content/uploads/2021/11/squat.gif",
                "https://i.pinimg.com/originals/28/1c/2d/281c2d4974439fed62678f50572b0c3f.gif",
                "https://media.tenor.com/Et-4FaGcKKYAAAAd/leg-extension-machine.gif",
                "https://www.inspireusafoundation.org/wp-content/uploads/2022/07/dumbbell-step-up.gif",
                "https://fitnessprogramer.com/wp-content/uploads/2022/01/Stiff-Leg-Deadlift.gif",
                "https://thumbs.gfycat.com/UnhealthyHeavyAffenpinscher-max-1mb.gif",
                "https://thumbs.gfycat.com/BleakDistortedCrocodile-size_restricted.gif",
                "https://fitnessprogramer.com/wp-content/uploads/2022/04/Standing-Barbell-Calf-Raise.gif",
                "https://genemedicshealth.s3-us-west-2.amazonaws.com/360/04091301-Dumbbell-Single-Leg-Calf-Raise_Calves_360.gif",
                "https://media.tenor.com/0hoNLcggDG0AAAAC/bench-press.gif",
                "https://media.tenor.com/gI-8qCUEko8AAAAM/pushup.gif",
                "https://hips.hearstapps.com/hmg-prod/images/workouts/2017/04/inclinedumbbellbenchpress-1492108229.gif",
                "https://hips.hearstapps.com/ame-prod-menshealth-assets.s3.amazonaws.com/main/assets/fly-dumbbell-incline.gif?resize=480:*",
                "https://fitnessprogramer.com/wp-content/uploads/2021/02/Pushdown.gif",
                "https://media.tenor.com/TgbVYDE_Ea4AAAAC/dokuz-close-grip-barbell-press.gif",
                "https://hips.hearstapps.com/ame-prod-menshealth-assets.s3.amazonaws.com/main/assets/skull.gif?resize=480:*",
                "https://thumbs.gfycat.com/FittingImpassionedAmethystinepython-max-1mb.gif",
                "https://images.squarespace-cdn.com/content/v1/5772ff709f745625b2a64e7f/1543430742993-X8R5JMJVXS42U5CC7XDD/Pallof+Press.gif",
                "https://hips.hearstapps.com/hmg-prod/images/workouts/2016/03/sideplank-1456956829.gif",
                "https://gymvisual.com/img/p/2/0/2/7/0/20270.gif",
                "https://thumbs.gfycat.com/ShortLimpAlleycat-max-1mb.gif",
                "https://149874912.v2.pressablecdn.com/wp-content/uploads/2020/12/Barbell-biceps-curl.gif",
                "https://hips.hearstapps.com/hmg-prod/images/workouts/2016/03/chinup-1457101678.gif",
                "https://hips.hearstapps.com/hmg-prod/images/workouts/2016/03/dumbbellcurl-1457043876.gif",
                "https://hips.hearstapps.com/hmg-prod/images/workouts/2016/03/hammercurl-1456956209.gif",
                "https://j.gifs.com/vlJr9V.gif",
                "https://www.inspireusafoundation.org/wp-content/uploads/2022/02/barbell-deadlift-movement.gif",
                "https://thumbs.gfycat.com/DecisiveDirtyAfghanhound-max-1mb.gif",
                "https://studio.code.org/media?u=https%3A%2F%2Fmusclewiki.com%2Fmedia%2Fuploads%2Fkettlebell-male-inclineshrugs-side.gif",
                "https://hips.hearstapps.com/hmg-prod/images/workouts/2016/03/barbelldeadlift-1457038089.gif",
                "https://i0.wp.com/www.strengthlog.com/wp-content/uploads/2020/05/back-extension-frontloaded.gif?fit=600%2C600&ssl=1",
                "https://flabfix.com/wp-content/uploads/2019/05/Superman.gif",
                "https://hips.hearstapps.com/ame-prod-menshealth-assets.s3.amazonaws.com/main/assets/bent-over-row.gif?resize=480:*",
                "https://thumbs.gfycat.com/DifficultPepperyBasenji-max-1mb.gif"
        };
        String[] descr= {"1.Take a roughly shoulder width grip. There should be a straight line from your elbow to fist (vertical forearms).\n" +
                "2.Pull your chin back and press the weight toward the ceiling by extending at the elbow joint and flexing at the shoulder joint.\n" +
                "3.Press until your elbows are extended and push your head forward slightly.\n" +
                "4.Return to the start position with control. Pulling your chin back to allow the bar to pass your face safely.",
                "1.Sit on a bench with back support. Raise the dumbbells to shoulder height with your palms forward.\n" +
                        "2.Raise the dumbbells upwards and pause at the contracted position.\n" +
                        "3.Lower the weights back to starting position.",
                "1.Use a handle attachment with the cable set all the way to the bottom of the machine.\n" +
                        "2.You should vertically abduct at the shoulder raising your arm straight out to the side.\n" +
                        "3.Raise until your arm is parallel with the ground and then back to the starting position.",
                "1.Take a double overhand roughly shoulder width grip.\n 2.Pull your elbows straight up the ceiling. Aim to get the bar to chin level or slightly higher.",
                "1.Sit on a bench with dumbbells in both hands, palms facing your body, back straight.\n" +
                        "2.Elevate your shoulders and hold the contracted position at the apex of the motion.\n" +
                        "3.Slowly lower your shoulders back to starting position.",
                "1.Stand tall with two dumbbells. Pull your shoulder blades up. Give a one second squeeze at the top.",
                "1.Stand with your feet shoulder width apart holding the Barbell with both hands in front just past shoulder width.\n" +
                        "2.Bend forward at the hips with a slight bend in your knees, keeping your back straight.\n" +
                        "3.Engage your shoulder blades, as if you are trying to touch them together.\n" +
                        "4.Release the shrug.",
                "1.Stand with your feet shoulder-width apart. Maintain the natural arch in your back, squeezing your shoulder blades and raising your chest.\n" +
                        "2.Grip the bar across your shoulders and support it on your upper back. Unwrack the bar by straightening your legs, and take a step back.\n" +
                        "3.Bend your knees as you lower the weight without altering the form of your back until your hips are below your knees.\n" +
                        "4.Raise the bar back to starting position, lift with your legs and exhale at the top.",
                "1.Step forward with one leg.\n" +
                        "2.Lower your body until your rear knee nearly touches the ground.\n" +
                        "3.Ensure you remain upright, and your front knee stay above the front foot.\n" +
                        "4.Push off the floor with your front foot until you return to the starting position. Switch legs.",
                "1.Sit on the machine with your back against the cushion and adjust the machine you are using so that your knees are at a 90 degree angle at the starting position.\n" +
                        "2.Raise the weight by extending your knees outward, then lower your leg to the starting position. Both movements should be done in a slow, controlled motion.",
                "1.Standing up straight, using a bench as a step, raise one foot onto the bench and hold the kettlebell in the same arm as the straight leg.\n" +
                        "2.Stand and bring both feet onto the bench. Slowly lower your leg back down to the starting position.\n" +
                        "3.Repeat.",
                "1.Stand with a barbell at your shins with your feet shoulder width apart.\n" +
                        "2.Bend forward at your hips and keep your knees as fully extended as possible.\n" +
                        "3.Grab the barbell and then extend your hips while maintaining a straight back.\n" +
                        "4.From the standing position, lower the weight in a controlled manner.\n" +
                        "5.You can either lower the weight to the floor or before you touch the floor, depending on your mobility.",
                "1.Lay down on the machine, placing your legs beneath the padded lever. Position your legs so that the padded lever is below your calve muscles.\n" +
                        "2.Support yourself by grabbing the side handles of the machine, and slowly raise the weight with your legs, toes pointed straight.\n" +
                        "3.Pause at the apex of the motion, then slowly return to starting position.",
                "1.Adjust the machine in accordance with your height and place your shoulders underneath the padded lever.\n" +
                        "2.The balls of your feet should be supporting your weight on the calve block, your heels extending off of it.\n" +
                        "3.Extend your heels upwards while keeping your knees stationary, and pause at the contracted position.\n" +
                        "4.Slowly return to the starting position. Repeat.",
                "1.Place the bar on your back\n" +
                        "2.Start with feet flat on the ground\n" +
                        "3.Extend your heels upwards while keeping your knees stationary, and pause at the contracted position.\n" +
                        "4.Slowly return to the starting position. Repeat.",
                "1.Standing straight with a kettlebell in one hand, lift the same foot as the side without the kettlebell off the floor.\n" +
                        "2.Raise your heel upwards while keeping your knees stationary.\n" +
                        "3.Pause when your heels are fully extended and then slowly return to the starting position and repeat.",
                "1.Lay flat on the bench with your feet on the ground. With straight arms unrack the bar.\n" +
                        "2.Lower the bar to your mid chest\n" +
                        "3.Raise the bar until you've locked your elbows.",
                "1.Place your hands firmly on the ground, directly under shoulders.\n" +
                        "2.Flatten your back so your entire body is straight and slowly lower your body\n" +
                        "3.Draw shoulder blades back and down, keeping elbows tucked close to your body\n" +
                        "4.Exhale as you push back to the starting position.",
                "1.Raise the bench to a 30 - 45 degree angle\n" +
                        "2.Lay on the bench and set your feet on the ground.\n" +
                        "3.Raise the dumbbells with straight arms then slowly lower them to about shoulder width.\n" +
                        "4.Raise them again until your arms are locked and at the starting position again.",
                "1.Lay flat on the bench and place your feet on the ground.\n" +
                        "2.Begin the exercise with the dumbbells held together above your chest, elbows slightly bent.\n" +
                        "3.Simultaneously lower the weights to either side.\n" +
                        "4.Pause when the weights are parallel to the bench, then raise your arms to the starting position.",
                "1.You can use any attachment for this. The cable should be set all the way at the top of the machine.\n" +
                        "2.Make sure to keep your upper arm glued at your side. Extend your elbows until you feel your triceps contract.",
                "1.Lay flat on the bench with your feet on the ground. With a narrow grip on the bar, straighten your arms\n" +
                        "2.Lower the bar to your lower-mid chest\n" +
                        "3.Slowly raise the bar until you've locked your elbows.",
                "1.Lay flat on the floor or a bench with your fists extended to the ceiling and a neutral grip.\n" +
                        "2.Break at the elbows until your fists are by your temples. Then extend your elbows and flex your triceps at the top.",
                "1.Grip the edge of the bench with your hands, Keep your feet together and legs straight.\n" +
                        "2.Lower your body straight down.\n" +
                        "3.Slowly press back up to the starting point.\n" +
                        "4.TIP: Make this harder by raising your feet off the floor and adding weight.",
                "1.Use a handle attachment, set the cable at nipple level, walk a few steps away, and face sideways.\n" +
                        "2.Start with both hands on the handle and your fists against your chest.\n" +
                        "3.Press the handle forward until your elbows are fully extended, then pull the handle back to your chest.",
                "1.Press your hand into the ground and pick up your hip off the ground.\n" +
                        "2.Open your chest and maintain this position for the allotted amount of time.",
                "1.Stand with a shoulder width stance. Begin with the dumbbell near one of your quads.\n" +
                        "2.Rotate at your upper spine to engage the obliques.\n" +
                        "3.You can pivot your back foot to keep from generating too much torque at the knee.",
                "1.Stand straight and hold the end of the Barbell\n" +
                        "2.Keep your hips and shoulders facing forward, bend to one side slowly\n" +
                        "3.Return to the upright position.\n" +
                        "4.Switch hands and repeat.",
                "1.While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out.\n" +
                        "2.Continue the movement until your biceps are fully contracted and the bar is at shoulder level.\n" +
                        "3.Hold the contracted position for a second and squeeze the biceps hard.\n" +
                        "4.Slowly bring the weight back down to the starting position.",
                "1.Grab the bar shoulder width apart with a supinated grip (palms facing you)\n" +
                        "2.With your body hanging and arms fully extended, pull yourself up until your chin is past the bar.\n" +
                        "3.Slowly return to starting position. Repeat.",
                "1.Stand up straight with a dumbbell in each hand at arm's length.\n" +
                        "2.Raise one dumbbell and twist your forearm until it is vertical and your palm faces the shoulder.\n" +
                        "3.Lower to original position and repeat with opposite arm",
                "1.Hold the dumbbells with a neutral grip (thumbs facing the ceiling).\n" +
                        "2.Slowly lift the dumbbell up to chest height\n" +
                        "3.Return to starting position and repeat.",
                "1.Grasp the bar with an overhand grip, arms and shoulders fully extended.\n" +
                        "2.Pull your body up until your chin is above the bar.\n" +
                        "3.Lower your body back to starting position.",
                "1.Stand with your mid-foot under the bar and grip the bar with your hands, about a shoulder width apart.\n" +
                        "2.Bend your knees, then lift the bar by straightening your back. It is important to keep your back straight.\n" +
                        "3.Stand to your full height and hold.\n" +
                        "4.Lower the bar to the floor by bending your knees and keeping your back straight.",
                "1.Stand with your mid-foot under the bar and grip the bar with your hands, about a shoulder width apart.\n" +
                        "2.Bend your knees, then lift the bar by straightening your back. It is important to keep your back straight.\n" +
                        "3.Stand to your full height and hold.\n" +
                        "4.Lower the bar to the floor by bending your knees and keeping your back straight.",
                "1.Brace your off arm against something stable (bench, box). Stagger your stance so your leg on the side of your working arm is back.\n" +
                        "2.Try to get your torso to parallel with the ground. That will extend your range of motion.\n" +
                        "3.Let your arm hang freely and then pull your elbow back. Imagine you've got a tennis ball in your armpit and squeeze it each rep.",
                "1.Leaning across the back section of the bench, with feet firmly on the floor and with arms hanging to the sides.\n" +
                        "2.Holding the kettlebell in both hands engage your shoulder blades, as if you are trying to touch them together. Release the shrug.",
                "1.Stand with your mid-foot under the bar and grip the bar with your hands, about a shoulder width apart.\n" +
                        "2.Bend your knees, then lift the bar by straightening your back. It is important to keep your back straight.\n" +
                        "3.Stand to your full height and hold.\n" +
                        "4.Lower the bar to the floor by bending your knees and keeping your back straight.",
                "1.Position your thighs on the padding and hook your feet on the platform supports.\n" +
                        "2.Keeping your back straight, slowly bend at your waist until your legs and back are at a 45° angle.\n" +
                        "3.Slowly raise your body to the starting position.",
                "1.Lie face down on the floor with your arms fully extended in front of you.\n" +
                        "2.Simultaneously raise your arms, legs and chest off of the floor and hold the position.\n" +
                        "3.Slowly lower your arms, legs and chest back to the starting position. Repeat.",
                "1.Grab a barbell with a shoulder width pronated or supinated grip.\n" +
                        "2.Bend forward at your hips while maintaining a flat back.\n" +
                        "3.Pull the weight toward your upper abdomen.\n" +
                        "4.Lower the weight in a controlled manner and repeat.",
                "1.Grip the bar with the palms facing forward, your hands need to be spaced out at a distance wider than shoulder width.\n" +
                        "2.As you have both arms extended in front of you holding the bar, bring your torso back around 30 degrees while sticking your chest out.\n" +
                        "3.Pull the bar down to about chin level or a little lower in a smooth movement whilst squeezing the shoulder blades together.\n" +
                        "4.After a second of squeezing, slowly raise the bar back to the starting position when your arms are fully extended."};
        int[] id_musle= {1,1,1,1,2,2,2,3,3,3,3,4,4,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,12,12};

        ContentValues cv = new ContentValues();
        //создаем таблицу
        db.execSQL("create table if not exists "+ TABLE_NAME + "("
                + KEY_ID+" integer primary key autoincrement, "+KEY_MUSCLE_ID +" integer, "
                +KEY_NAME+" text, "+KEY_DESCRIPTION+" text, "+KEY_PHOTO+" text"+")");

        Log.d("mLog","Created");
        //заполняем её
        for(int i =0;i< name_exer.length;i++){
            cv.clear();
            cv.put(KEY_MUSCLE_ID, id_musle[i]);
            cv.put(KEY_NAME, name_exer[i]);
            cv.put(KEY_DESCRIPTION, descr[i]);
            cv.put(KEY_PHOTO, photo[i]);
            db.insert(TABLE_NAME, null, cv);
            Log.d("mLog","added");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }
}
