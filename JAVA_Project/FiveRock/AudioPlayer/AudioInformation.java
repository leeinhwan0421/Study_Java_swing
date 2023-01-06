package FiveRock.AudioPlayer;

public class AudioInformation 
{
    private static AudioInformation instance;

	private AudioInformation() {}

	public static AudioInformation getInstance()
	{
		if (instance == null) 
            instance = new AudioInformation();

		return instance;
	}

    String mainBackGroundMusicFilePath = "FiveRock/AudioSource/backGround.wav";
}
