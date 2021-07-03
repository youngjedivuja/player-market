package com.example.transfers.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(code = BAD_REQUEST, reason = "Transfer cannot be completed")
public class IllegalTeamTransferException extends RuntimeException{
}
