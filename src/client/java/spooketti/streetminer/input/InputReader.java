package spooketti.streetminer.input;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.dialog.Input;

import java.util.ArrayList;

public class InputReader {

    static class InputTick
    {
        public char input;
        public int tick;

        public InputTick(char input, int tick)
        {
            this.input = input;
            this.tick = tick;
        }
    }

    public ArrayList<InputTick> buffer = new ArrayList<>();

    private char lastInput = 'x';

    StringBuilder sb = new StringBuilder();

    public void register()
    {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(client.player == null)
            {
                return;
            }
            var input = client.player.input;
            char readInput = 'x';

            /*
            x = neutral
            f = forward
            S = down
            b = back
            F = forward down diagonal
            S = back down diagonal
             */
            boolean onlyDown = input.keyPresses.shift() && !(input.keyPresses.forward() || input.keyPresses.backward());

            if(input.keyPresses.forward())
            {
                readInput = 'f';
            }

            if(input.keyPresses.backward())
            {
                readInput = 'b';
            }

            if(input.keyPresses.shift() && !onlyDown)
            {
                readInput = Character.toUpperCase(readInput);
            }

            if(lastInput != readInput)
            {
                buffer.add(new InputTick(readInput, client.player.tickCount));
            }

            if(buffer.size() > 20)
            {
                buffer.removeFirst();
            }

            sb.setLength(0);

            for (InputTick c : buffer)
            {
                sb.append(c.input);
                sb.append("\t");
            }

            client.player.sendSystemMessage(Component.translatable(sb.toString()));

            lastInput = readInput;
        });
    }

    /*
    [11:31:51] [Render thread/INFO] (Minecraft) [System] [CHAT] f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x
[11:31:51] [Render thread/INFO] (Minecraft) [System] [CHAT] f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x
[11:31:51] [Render thread/INFO] (Minecraft) [System] [CHAT] f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x
[11:31:51] [Render thread/INFO] (Minecraft) [System] [CHAT] f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x
[11:31:51] [Render thread/INFO] (Minecraft) [System] [CHAT] f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x
[11:31:51] [Render thread/INFO] (Minecraft) [System] [CHAT] f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x
[11:31:51] [Render thread/INFO] (Minecraft) [System] [CHAT] f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x
[11:31:51] [Render thread/INFO] (Minecraft) [System] [CHAT] f	x	F	x	f	x	F	x	f	x	F	x	f	x	F	x
     */
}
