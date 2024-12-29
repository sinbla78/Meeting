package com.example.decofolio.domain.meeting.exception;

import com.example.decofolio.global.error.exception.CustomException;
import com.example.decofolio.global.error.exception.ErrorCode;

public class MeetingNotFoundException extends CustomException {

    public static final MeetingNotFoundException EXCEPTION =
            new MeetingNotFoundException();

    private MeetingNotFoundException() {
        super(ErrorCode.MEETING_NOT_FOUND);
    }
}
