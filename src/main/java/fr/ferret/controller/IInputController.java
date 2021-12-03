package fr.ferret.controller;

public interface IInputController
{
    /**
     * Validates input and runs the program if it's valid
     * @param fileNameAndPath The "save to" file path
     */
    void validateInfosAndRun(String fileNameAndPath);
}
