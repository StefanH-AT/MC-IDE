package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.enums.ContentType;
import at.tewan.mcide.project.Project;

public class ContentNode extends CommandNode {

    // Content node ist zum beispiel minecraft:stone oder minecraft:unbreaking

    private String namespace;
    private String content;

    private String mode;

    private static final String ONLY_MINECRAFT = "mc-only";

    public ContentNode(String mode, ContentNode... nodes) {
        super(nodes);
        this.mode = mode;

    }

    @Override
    public String getCompletion() {
        String completions = "minecraft: ";

        if(!mode.equals(ONLY_MINECRAFT)) {
            // Alle Namespace aus dem Projekt zurückgeben (Durch Leerzeichen Terminiert)
            for (String nspace : Project.getNamespaces()) {
                completions += "#" + nspace + ": ";
            }
        }

        return completions;
    }
}

