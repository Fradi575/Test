package brickset;

import repository.Repository;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }
    /**
     * Returns the number of LEGO sets with the tag specified.
     *
     * @param tag a LEGO set tag
     * @return the number of LEGO sets with the tag specified
     */
    public long countLegoSetsWithTag(String tag) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTags() != null && legoSet.getTags().contains(tag))
                .count();
    }
    /**
     * Visszaadjuk a legnagyobb számú legó számát
     * @return the max number of LEGO number
     * */
    public int lego_szama(){
        return getAll().stream()
                .max(Comparator.comparingInt(LegoSet::getPieces))
                .get().getPieces();
    }
    /**
     * Kiiratjuk azokat a Lego játékokat amik az adott témával rendelkeznek
     * @param theme a kívánt lego játék témája
     *
     * */
    public void printLegoSetsByTheme(String theme){
        getAll().stream()
                .filter(legoSet -> legoSet.getTheme().equals(theme))
                .map(LegoSet::getName)
                .forEach(System.out::println);
    }

    /**
     * Visszaadunk egy listát ami tartalmazza azokat a Legokat amik a két év közt kerültek kiadásra
     * @param min kezdő év
     * @param max záró év
     * @return list of legosets between the two years
     * */
    public List<LegoSet> getLegoSetBetweenYears(Year min, Year max){
        return getAll().stream()
                .filter(legoSet -> legoSet.getYear().isAfter(min) && legoSet.getYear().isBefore(max))
                .collect(Collectors.toList());
    }
    /**
     * Kiiratjuk azokat a Lego játékokat amiknek nincs megadva subtypeja
     *
     *
     * */
    public void printLegoSetWithNullSubType(){
        getAll().stream()
                .filter(legoSet -> legoSet.getSubtheme()==null)
                .map(LegoSet::getName)
                .forEach(System.out::println);
    }

    /**
     * Megszámoljuk hány legoset lett az adott csomagolásban kiadva
     * @param packagingType a csomagolás típusa
     *
     * @return count of legosets packed in the given packagingtype
     * */
    public int countLegoSetsbyPackingingType(PackagingType packagingType){
        return (int) getAll().stream()
                .filter(legoSet -> legoSet.getPackagingType().equals(packagingType))
                .map(LegoSet::getName)
                .count();
    }






// ...

    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println(repository.countLegoSetsWithTag("Microscale"));
        System.out.println(repository.lego_szama());
        repository.printLegoSetsByTheme("SpongeBob SquarePants");
        for (LegoSet legoSet : repository.getLegoSetBetweenYears(Year.of(2005), Year.of(2010))){
            System.out.println(legoSet.getName());
        }
        System.out.println("\n\n\nLegok ismeretlen subtype-val:\n\n\n");
        repository.printLegoSetWithNullSubType();
        System.out.println(repository.countLegoSetsbyPackingingType(PackagingType.BUCKET));
    }
}


