package be.skydragonsz.discord.audio;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class AudioTrackWrapper <T> {
    private final AudioTrack track;
    private final T requester;
    private final TrackType type;

    public enum TrackType {
        NORMAL_REQUEST,
        PLAYLIST,
        FM,
        RADIO;

    }

    public AudioTrackWrapper(AudioTrack track, T requester, TrackType type) {
        this.track = track;
        this.requester = requester;
        this.type = type;
    }

    public AudioTrackWrapper() {
        this.track = null;
        this.requester = null;
        this.type = null;
    }

    public boolean isEmpty() {
        if (track == null)
            return true;
        return false;
    }

    @SuppressWarnings("unchecked")
    public AudioTrackWrapper makeClone() {
        return new AudioTrackWrapper(this.track.makeClone(), this.requester, this.type);
    }

    public AudioTrack getTrack() {
        return track;
    }

    public T getRequester() {
        return requester;
    }

    public TrackType getType() {
        return type;
    }
}