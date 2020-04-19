package ru.cetelem.cli.extensions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandFormatException;
import org.jboss.as.cli.CommandLineException;
import org.jboss.as.cli.impl.ArgumentWithValue;
import org.jboss.as.cli.operation.ParsedCommandLine;


public class DownloadCommandHandler extends org.jboss.as.cli.handlers.CommandHandlerWithHelp{

	public static final String NAME = "download";
   
	private ArgumentWithValue url;
	private ArgumentWithValue dst;	
 
    public DownloadCommandHandler(CommandContext ctx)  {
        super(NAME, false);
        
        
    	
        url = new ArgumentWithValue(this, null, "--url") {
            @Override
            public boolean canAppearNext(CommandContext ctx) throws CommandFormatException {
                return super.canAppearNext(ctx);
            }
        };
        
        dst = new ArgumentWithValue(this, null, "--dst") {
                @Override
                public boolean canAppearNext(CommandContext ctx) throws CommandFormatException {
                    return super.canAppearNext(ctx);
                }
            };        
    	        
    }
 
 
    @Override
    protected void doHandle(CommandContext ctx) throws CommandLineException {
        final ParsedCommandLine parsedLine = ctx.getParsedCommandLine();

		
        if(!url.isPresent(parsedLine)) {
            throw new CommandFormatException("The --url is mandatory. ");
        }

        String urlString = url.getValue(parsedLine, true);
        String dstString = Optional.ofNullable(dst.getValue(parsedLine, false)).orElse(".");
        
        
        try {
        	Path downloadFile = download(ctx, urlString, dstString);
			ctx.printLine(String.format("Downloaded file '%s' to '%s'", 
					downloadFile.getFileName(), downloadFile.getParent().toAbsolutePath()));
				
		} catch (IOException e) {
			ctx.printLine(String.format("Error! %s", e.getMessage()));
		}
    }
    
    
    private Path download(CommandContext ctx, String sourceURL, String targetDirectory) throws IOException
    {
        URL url = new URL(sourceURL);
        String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());
        Path targetPath = new File(targetDirectory + File.separator + fileName).toPath();
        
        if(targetPath.toFile().exists())
        	ctx.printLine(String.format("Warning! File '%s' already exists in '%s'. It will be replaced", 
        			targetPath.getFileName(),  targetPath.getParent().toAbsolutePath()));
        
        Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return targetPath;
    }    
    
}
