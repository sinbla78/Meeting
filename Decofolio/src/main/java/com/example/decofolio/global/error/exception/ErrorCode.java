package com.example.decofolio.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //401에러
    EXPIRED_JWT(401, "Expired Token"),
    INVALID_JWT(401, "Invalid Token"),
    PASSWORD_MISMATCH(401, "Password Mismatch"),
    CANNOT_BE_DELETED(401, "Cannot Be Deleted"),
    CANNOT_BE_MODIFIED(401, "Cannot Be Modified"),
    INVALID_COMMENT(401, "Invalid Comment"),

    //204에러
    USER_NOT_FOUND(204, "User Not Found"),
    REFRESH_TOKEN_NOT_FOUND(204, "Refresh Token Not Found"),
    FEED_NOT_FOUND(204, "Feed Not Found"),
    PROJECT_NOT_FOUND(204, "Project Not Found"),
    LINK_NOT_FOUND(204, "Link Not Found"),
    COMMENT_NOT_FOUND(204, "COMMENT_NOT_FOUND"),
    MEETING_NOT_FOUND(204, "Meeting Not Found"),
    FEED_VIEW_COUNT_NOT_FOUND(204, "Feed View Count Not Found"),

    //401에러
    WRITER_MISMATCH(401, "Writer Mismatch"),

    //409에러
    USER_EXISTS(409, "User Already Exists"),
    USER_ALREADY_PARTICIPATING(409, "Already created or joined a meeting"),

    //500에러
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int status;
    private final String message;
}
