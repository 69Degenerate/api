import javax.speech.*;      
import java.util.*;      
import javax.speech.synthesis.*;      
public class TextToSpeechExample2      
{      
//text to listen  
String speaktext;   
//function that makes text audible  
public void dospeak(String speak)      
{      
//assigning text to speak variable  
speaktext=speak;      
String voiceName ="kevin16";      
try      
{      
//the SynthesizerModeDesc class inherits the EngineModeDesc with properties  
//it inherits the engine name, mode name, locale, and running properties   
SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general",  Locale.US, null, null);      
//Synthesizer interface generates sound and the createSynthesizer() method creates the Synthesizer  
Synthesizer synthesizer =  Central.createSynthesizer(desc);      
//allocates a Synthesizer  
synthesizer.allocate();      
//resumes a Synthesizer  
synthesizer.resume();       
desc = (SynthesizerModeDesc)  synthesizer.getEngineModeDesc();       
Voice[] voices = desc.getVoices();        
Voice voice = null;  
//loop iterates over the voice until the condition becomes false  
for (int i = 0; i < voices.length; i++)      
{      
if (voices[i].getName().equals(voiceName))      
{      
voice = voices[i];      
break;       
}       
}      
synthesizer.getSynthesizerProperties().setVoice(voice);      
System.out.print("Speaking: "+speaktext);      
synthesizer.speakPlainText(speaktext, null);      
synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);      
synthesizer.deallocate();      
}      
catch (Exception e)     
{      
String message = " missing speech.properties in " + System.getProperty("user.home") + "\n";      
System.out.println(""+e);      
System.out.println(message);      
}      
}      
public static void main(String args[])      
{      
TextToSpeechExample2 obj=new TextToSpeechExample2();
Scanner sc = new Scanner(System.in);
System.out.println("what do you want me to speak");
String t=sc.nextLine();
obj.dospeak(t);      

}      
}  