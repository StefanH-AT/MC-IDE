package at.tewan.mcide.mcfunction.command;

import at.tewan.mcide.enums.ContentType;
import at.tewan.mcide.project.Project;

public class ContentNode extends CommandNode {

    // Content node ist zum beispiel minecraft:stone oder minecraft:unbreaking

    private ContentType type;
    private String namespace;
    private String content;

    public ContentNode(ContentType type, String raw, ContentNode... nodes) {
        super(nodes);
        this.type = type;

        if(raw == null) return;
        String[] splitted = raw.split(":");
        namespace = splitted[0];
        content = splitted[1];
    }

    public ContentNode(String raw, ContentNode... nodes) {
        this(null, raw, nodes);
    }

    @Override
    public String getCompletion() {
        String completions = "minecraft: ";

        // Alle Namespace aus dem Projekt zurückgeben (Durch Leerzeichen Terminiert)
        for(String nspace : Project.getNamespaces()) {
            completions += nspace + ": ";
        }

        return completions;
    }
}

