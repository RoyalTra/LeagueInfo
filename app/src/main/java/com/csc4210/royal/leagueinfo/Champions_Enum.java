package com.csc4210.royal.leagueinfo;

/**
 * Created by Royal on 4/13/2018.
 */

public final class Champions_Enum {
    public enum Champ  {
                Aatrox , Ahri ,
                Akali , Alistar ,
                Amumu , Anivia ,
                Annie , Ashe ,
                AurelionSol , Azir ,
                Bard , Blitzcrank ,
                Brand , Braum ,
                Caitlyn , Camille ,
                Cassiopeia , Chogath ,
                Corki , Darius ,
                Diana , Draven ,
                DrMundo , Ekko ,
                Elise , Evelynn ,
                Ezreal , FiddleSticks ,
                Fiora , Fizz ,
                Galio , Gangplank ,
                Garen , Gnar ,
                Gragas , Graves ,
                Hecarim , Heimerdinger ,
                Illaoi , Irelia ,
                Ivern , Janna ,
                JarvanIV , Jax ,
                Jayce , Jhin ,
                Jinx , Kalista ,
                Karma , Karthus ,
                Kayle , Kennen ,
                Khazix , Kindred ,
                Kled , KogMaw ,
                Leblanc , LeeSin ,
                Leona , Lissandra ,
                Lucian , Lulu ,
                Lux , Malphite ,
                Malzahar , Maokai ,
                MasterYi , MissFortune ,
                Mordekaiser , Morgana ,
                Nami , Nasus ,
                Nautilus , Nidalee ,
                Nocturne , Nunu ,
                Olaf , Orianna ,
                Pantheon , Poppy ,
                Quinn , Rammus ,
                RekSai , Renekton ,
                Rengar , Riven ,
                Rumble , Ryze ,
                Sejuani , Shaco ,
                Shen , Shyvana ,
                Singed , Sion ,
                Sivir , Skarner ,
                Sona , Soraka ,
                Swain , Syndra ,
                TahmKench , Taliyah ,
                Talon , Taric ,
                Teemo , Thresh ,
                Tristana , Trundle ,
                Tryndamere , TwistedFate ,
                Twitch , Udyr ,
                Urgot , Varus ,
                Vayne , Veigar ,
                Velkoz , Vi ,
                Viktor , Vladimir ,
                Volibear , Warwick ,
                MonkeyKing , Xerath ,
                XinZhao , Yasuo ,
                Yorick , Zac ,
                Zed , Ziggs ,
                Zilean , Zyra

    }

    public static int getSize(){
        int count = 0;
        for(Champions_Enum.Champ champ : Champions_Enum.Champ.values())
            count++;

        return count;
    }


}
