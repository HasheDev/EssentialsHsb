package not.domain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.KickCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
public class Home extends JavaPlugin{
    String plname = ChatColor.BLUE + "[" + "Essentials" + ChatColor.DARK_GREEN + " Hsb " + ChatColor.BLUE + "]"; //name this plugin
    String plversion = ChatColor.DARK_GREEN + "1"+ChatColor.BLUE+"."+ChatColor.DARK_GREEN+"0";
    String pldev = "Hashing";
    String permission;
	public ConsoleCommandSender warn = Bukkit.getConsoleSender(); //warns
	HashMap<Player, Player> playertpa = new HashMap<Player, Player>(); //HashMap
	
	@Override
	public void onEnable() {
		warn.sendMessage(plname);
		warn.sendMessage(ChatColor.BLUE+"*"+ChatColor.DARK_GREEN + " ativado com sucesso");
		warn.sendMessage(plversion);
	}
	@Override
	public void onDisable() {
		warn.sendMessage(plname);
		warn.sendMessage(ChatColor.BLUE+"* "+ ChatColor.RED + "desligado com sucesso");
	}
	@Override
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player) ) {
			sender.sendMessage("Só jogadores podem usar o comando");
			return true;
		}else {
		 Player p = (Player) sender;
		 if(command.getName().equalsIgnoreCase("essentialshsb")) {
				p.sendMessage(plname);
				p.sendMessage(plversion);
				p.sendMessage(ChatColor.BLUE+ " [Download] > " + ChatColor.DARK_GREEN + "https://github.com/HasheDev/EssentialsHsb/releases/tag/1.0");
			}else if(command.getName().equalsIgnoreCase("essentialshsbhelp")) {
				permission = "essentialshsb.help";
				if(p.hasPermission(permission)){
					ChatColor cmdcolor = ChatColor.GREEN;
					p.sendMessage(ChatColor.DARK_RED+"/gm: "+cmdcolor+"mudar o modo de jogo");
					p.sendMessage(ChatColor.DARK_RED+"/fly: "+cmdcolor+"mudar modo de voar");
					p.sendMessage(ChatColor.DARK_RED+"/tpa: "+cmdcolor+"fazer pedido de teleporte");
				}else {
					p.sendMessage("Você não tem a permissão: "+permission + " para usar este comando");
				}
			}else if(command.getName().equalsIgnoreCase("kick")) {
				if(args.length == 0) {
					p.sendMessage("Use: /kick player");
				}else if(args.length > 0) {
					Player player = (Player) sender;
					Player target = player.getServer().getPlayer(args[0]);
					if(target != null) {
					if(args.length == 1) {
					   if(args[0].equalsIgnoreCase(args[0])) {
							target.kickPlayer("Confidencial");
					   }
					}else if(args.length == 2) {
						target.kickPlayer(args[1]);
					  }
					}else {
						p.sendMessage("Player não esta conectado");
					}
				}
			}
		 else if(command.getName().equalsIgnoreCase("gm") | command.getName().equalsIgnoreCase("gamemode")) {
			 permission = "essentialshsb.gm";
			 if(p.hasPermission(permission)) {
				if(args.length == 1) {
					  try {
						  int Gm = Integer.parseInt(args[0]);
						  p.sendMessage("Use: /gm 1a2 ou /gamemode 1a2");
						  if(Gm >= 0) {
								if(Gm == 0) {
									if(p.getGameMode() != GameMode.SURVIVAL) {
									p.sendMessage("Você trocou de modo de jogo de " + p.getGameMode() + " para Survival");
									p.setGameMode(GameMode.SURVIVAL);
									}
									else
									p.sendMessage("Você já esta no modo de jogo Survival");
								}else if(Gm == 1) {
									if(p.getGameMode() != GameMode.CREATIVE) {
									p.sendMessage("Você trocou de modo de jogo de " + p.getGameMode() + " para Criativo");
									p.setGameMode(GameMode.CREATIVE);
									}
									else
									p.sendMessage("Você já esta no modo de jogo Criativo");
								}else if(Gm == 2 | Gm == 3) {
									if(p.getGameMode() != GameMode.SPECTATOR) {
									p.sendMessage("Você trocou de modo de jogo de " + p.getGameMode() + " para Espectador");
									p.setGameMode(GameMode.SPECTATOR);
									}
									else
									p.sendMessage("Você já esta no modo de jogo Espectador");
								}
						}
					  }catch (NumberFormatException ex) {
						  p.sendMessage("/gm "+args[0]+" Não é um modo de jogo");
					  }
				}
			 }else {
				 p.sendMessage("Você não tem a permissão: "+ permission + " para usar este comando");
			 }
			}else if(command.getName().equalsIgnoreCase("fly")) {
				permission = "essentialshsb.fly";
				if(p.hasPermission(permission)) {
					if(p.getAllowFlight()) {
						p.setAllowFlight(false);
						p.sendMessage("Sua habilidade de voar foi desativada");
					}else {
						p.setAllowFlight(true);
						p.sendMessage("Sua habilidade de voar foi ativada");
					}
				}else {
					p.sendMessage("Você não tem a permissão: "+ permission + " para poder voar");
				}
			}
		 else if(command.getName().equalsIgnoreCase("tpa")) {
			if(args.length == 1) {
				Player target = p.getServer().getPlayer(args[0]);			  
				if(target != null) {
					if(!target.getName().equals(p.getName()))
					{
						playertpa.put(target, p);
					p.sendMessage("Você pediu tpa para " + target.getName());
					target.sendMessage(p.getName()+ " esta le pedindo teleporte use /tpaccept ou /tpadeny");
					}

				}
			}
		}else if(command.getName().equalsIgnoreCase("tpaccept")) {
			if(playertpa.get(p) != null) {
				playertpa.get(p).teleport(p);
				p.sendMessage("Você aceitou o tpa de " + playertpa.get(p).getName());
				playertpa.get(p).sendMessage("Seu tpa foi aceito " + playertpa.get(p).getName());
				playertpa.put(p, null);
			}
		}else if(command.getName().equalsIgnoreCase("tpadeny")) {
			if(playertpa.get(p) != null) {
				p.sendMessage("Você cancelou o pedido de teleporte de " + playertpa.get(p).getName());
				playertpa.get(p).sendMessage("Seu tpa não foi aceito " + playertpa.get(p).getName());
				playertpa.put(p, null);
			}
		}
		}
		return false;
	}
		
}
