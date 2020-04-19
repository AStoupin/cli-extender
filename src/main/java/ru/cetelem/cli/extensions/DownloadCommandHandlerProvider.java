package ru.cetelem.cli.extensions;

import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandHandler;

public class DownloadCommandHandlerProvider implements org.jboss.as.cli.CommandHandlerProvider {
	 
	 
    
    public CommandHandler createCommandHandler(CommandContext ctx) {
        return new DownloadCommandHandler(ctx);
    }
 
 
    /**
     * Whether the command should be available in tab-completion.
     */
    
    public boolean isTabComplete() {
        return true;
    }
 
 
    /**
     * Command name(s).
     */
    
    public String[] getNames() {
        return new String[]{DownloadCommandHandler.NAME};
    }
}