package net.jp.minecraft.plugin;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

/**
 * ゲームモードをトグルするだけのプラグイン
 * ToggleGameMode
 * @author syokkendesuyo
 */


public class ToggleGameMode extends JavaPlugin implements Listener {


	/**
	 * プラグインが有効になったときに呼び出されるメソッド
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);

		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			// ｽﾃｲﾀｽの送信に失敗 :-(
		}

	}

	//コマンドで杖を渡す処理
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tgm")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Please excute this /tgm command on a game!");
				sender.sendMessage("/ait コマンドはゲーム内で実行してください。");
			}
			else {
				Player player = (Player) sender;
				if(player.hasPermission("tgm.use")||player.isOp()){
					if(player.getGameMode() == GameMode.SURVIVAL){
						player.setGameMode(GameMode.CREATIVE);
						player.sendMessage(ChatColor.AQUA + "[情報]ゲームモードをクリエイティブにしました。");
					}
					else if(player.getGameMode() == GameMode.CREATIVE){
						player.setGameMode(GameMode.ADVENTURE);
						player.sendMessage(ChatColor.AQUA + "[情報]ゲームモードをアドベンチャーにしました。");
					}
					else if(player.getGameMode() == GameMode.ADVENTURE){
						player.setGameMode(GameMode.SURVIVAL);
						player.sendMessage(ChatColor.AQUA + "[情報]ゲームモードをサバイバルにしました。");
					}
				}
			}
			return true;
		}
		return false;
	}
}
