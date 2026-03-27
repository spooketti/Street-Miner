package spooketti.streetminer.input;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.dialog.Input;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private static String hadoukenInput = "SFf";
    private static String tatsumakiInput = "bBS";
    private static String shoryuInput = "fSF";
    private static LocalPlayer player;

    public enum inputType
    {

    }

    StringBuilder sb = new StringBuilder();

    public static void inputMatcher()
    {
        StringBuilder inputToString = new StringBuilder();
        List<InputTick> read = buffer.reversed();
        int totalTime = 0;
        for(int i=0;i<read.size();i++)
        {
            totalTime += read.get(i).tick;
            if(read.get(i).input == 'x')
            {
                continue;
            }

            inputToString.append(read.get(i).input);
            /*
            if(hasSuper)
             */
            if(inputToString.reverse().toString().equals(hadoukenInput))
            {
                player.sendSystemMessage(Component.translatable("hadouken"));
                buffer.clear();
                break;
            }

            if(inputToString.reverse().toString().equals(tatsumakiInput))
            {
                player.sendSystemMessage(Component.translatable("tatsumaki"));
                buffer.clear();
                break;
            }
            if(inputToString.reverse().toString().equals(shoryuInput))
            {
                player.sendSystemMessage(Component.translatable("shoryuken"));
                buffer.clear();
                break;
            }
            player.sendSystemMessage(Component.translatable(inputToString.reverse().toString()));
        }
        // player.sendSystemMessage(Component.translatable(inputToString.substring(0,4)));
    }

    public static ArrayList<InputTick> buffer = new ArrayList<>();

    private char lastInput = 'x';

    public void register()
    {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(client.player == null)
            {
                return;
            }
            player = client.player;
            var input = client.player.input;
            char readInput = 'x';

            /*
            x = neutral
            f = forward
            S = down
            b = back
            F = forward down diagonal
            B = back down diagonal
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

            if(onlyDown)
            {
                readInput = 's';
            }

            if(input.keyPresses.shift())
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
            }

//            client.player.sendSystemMessage(Component.translatable(sb.reverse().toString()));
//            client.player.onAttack();

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
