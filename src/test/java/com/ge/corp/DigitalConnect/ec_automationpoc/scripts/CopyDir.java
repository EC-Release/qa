package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class CopyDir extends SimpleFileVisitor<Path> {
	private Path sourceDir;
	private Path targetDir;

	public CopyDir(Path sourceDir, Path targetDir) {
		this.sourceDir = sourceDir;
		this.targetDir = targetDir;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {

		try {
			Path targetFile = targetDir.resolve(sourceDir.relativize(file));
			Files.copy(file, targetFile);
		} catch (IOException ex) {
			System.err.println(ex);
		}

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) {
		try {
			Path newDir = targetDir.resolve(sourceDir.relativize(dir));
			Files.createDirectory(newDir);
		} catch (IOException ex) {
			System.err.println(ex);
		}

		return FileVisitResult.CONTINUE;
	}

	public void createCopyDir(String sourceDirectory, String targetDirectory) throws IOException {
		Path sourceDir = Paths.get(sourceDirectory);
		Path targetDir = Paths.get(targetDirectory);
		Files.walkFileTree(sourceDir, new CopyDir(sourceDir, targetDir));

	}

	public static void main(String[] args) throws IOException {
		Path sourceDir = Paths.get("C:\\Users\\sareddyc\\Test123");
		Path targetDir = Paths.get("C:\\Users\\sareddyc\\Testcopydir\\Test123");

		Files.walkFileTree(sourceDir, new CopyDir(sourceDir, targetDir));
	}

}
