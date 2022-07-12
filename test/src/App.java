package test.src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class App {
    static void time() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        speak("The time is " + dtf.format(now));
    }

    static void whishme() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        String w = "";
        if (hour >= 0 && hour < 12) {
            // System.out.println("Good morning");
            w = "Good morning";
        } else if (hour >= 12 && hour < 18) {
            // System.out.println("Good Afternoon");
            w = "Good Afternoon";
        } else {
            // System.out.println("Good Afternoon");
            w = "Good afternoon";
        }
        speak(w);
    }

    static void speak(String com) {
        try {
            // setting properties as Kevin Dictionary
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
            // registering speech engine
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
            // create a Synthesizer that generates voice
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            // allocates a synthesizer
            synthesizer.allocate();
            // resume a Synthesizer
            synthesizer.resume();
            // speak the specified text until the QUEUE become empty
            synthesizer.speakPlainText(com, null);
            System.out.println(com);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        try (Scanner sc = new Scanner(System.in)) {
            whishme();
            // speak(g);
            speak("welcome sir ");
            speak("how may i help you");
            System.out.println("(type 'help' for commands)");
            while (true) {
                System.out.print("\n > ");
                sc.skip("(\r\n|[\n\r\u0085])?");
                String com = sc.next();
                System.out.print(">> ");
                sc.skip("(\r\n|[\n\r\u0085])?");
                if (com.equals("name")) {
                    speak("rudwig");
                } else if (com.equals("work")) {
                    speak("penetration");
                } else if (com.equals("quit")) {
                    speak("fuck offf");
                    break;
                } else if (com.equals("time")) {
                    time();
                } else {
                    System.out.println("please give appropriate queries");
                }

            }
        }

    }
}