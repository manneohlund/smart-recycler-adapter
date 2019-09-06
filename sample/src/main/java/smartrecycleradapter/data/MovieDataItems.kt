package smartrecycleradapter.data

/*
 * Created by Manne Öhlund on 2019-06-19.
 * Copyright (c) All rights reserved.
 */

import smartrecycleradapter.models.MovieModel
import java.util.*
import java.util.Arrays.asList

object MovieDataItems {

    const val POSTER_BASE_URL = "https://raw.githubusercontent.com/manneohlund/smart-recycler-adapter-resources/master/posters/"
    const val BANNER_BASE_URL = "https://raw.githubusercontent.com/manneohlund/smart-recycler-adapter-resources/master/banners/"
    const val THUMBS_BASE_URL = "https://raw.githubusercontent.com/manneohlund/smart-recycler-adapter-resources/master/thumbs/"

    // Coming soon
    val comingSoonItems: MutableList<Any> = mutableListOf(
            MovieModel("Fist man", "first_man"),
            MovieModel("Terminator dark fate", "terminator_dark_fate"),
            MovieModel("Murder Mystery", "murder_mystery"),
            MovieModel("Joker", "joker"),
            MovieModel("The Lion king", "lion_king"),
            MovieModel("Wonder Woman", "woderwoman"),
            MovieModel("It 2", "it_2"),
            MovieModel("John Wick 3", "john_wick_3"),
            MovieModel("Grinch", "grinch"),
            MovieModel("Hellboy", "hellboy"),
            MovieModel("Age of uprising", "age_of_uprising"),
            MovieModel("Beachbum", "beachbumposter"),
            MovieModel("Bernadette", "bernadette"),
            MovieModel("Bohemian rhapsody", "bohemian_rhapsody"),
            MovieModel("Once upon a time in Hollywood", "once_hollywood"),
            MovieModel("Rambo last blood", "rambo_last_blood"),
            MovieModel("Predator", "predator"),
            MovieModel("Godzilla king of the mosters", "godzilla_king_of_the_monsters"),
            MovieModel("Godzilla", "godzilla"),
            MovieModel("Abominable", "abominable")
    )

    // Load more items
    val loadMoreItems: MutableList<Any> = asList(
            MovieModel("Terminator dark fate", "terminator_dark_fate"),
            MovieModel("Joker", "joker"),
            MovieModel("Fist man", "first_man"),
            MovieModel("The Lion king", "lion_king"),
            MovieModel("John Wick 3", "john_wick_3"),
            MovieModel("Grinch", "grinch"),
            MovieModel("Wonder Woman", "woderwoman"),
            MovieModel("Hellboy", "hellboy"),
            MovieModel("Predator", "predator"),
            MovieModel("It 2", "it_2"),
            MovieModel("Once upon a time in Hollywood", "once_hollywood"),
            MovieModel("Age of uprising", "age_of_uprising"),
            MovieModel("Murder Mystery", "murder_mystery")
    )

    // My watchlist
    val myWatchListItems: MutableList<Any> = mutableListOf(
            MovieModel("Joker", "joker"),
            MovieModel("Terminator dark fate", "terminator_dark_fate"),
            MovieModel("John Wick 3", "john_wick_3"),
            MovieModel("Bohemian rhapsody", "bohemian_rhapsody"),
            MovieModel("Fist man", "first_man"),
            MovieModel("Once upon a time in Hollywood", "once_hollywood"),
            MovieModel("Murder Mystery", "murder_mystery"),
            MovieModel("It 2", "it_2"),
            MovieModel("Hellboy", "hellboy"),
            MovieModel("Beachbum", "beachbumposter"),
            MovieModel("Predator", "predator"),
            MovieModel("Rambo last blood", "rambo_last_blood")
    )

    // Action
    var nestedActionItems: MutableList<Any> = asList(
            MovieModel("Fury", "fury"),
            MovieModel("Edge of tomorrow", "edge_of_tomorrow"),
            MovieModel("Godzilla king of the monsters", "godzilla_king_of_the_monsters"),
            MovieModel("Predator", "predator"),
            MovieModel("Godzilla", "godzilla"),
            MovieModel("Rambo last blood", "rambo_last_blood"),
            MovieModel("Hellboy", "hellboy"),
            MovieModel("Terminator dark fate", "terminator_dark_fate"),
            MovieModel("300 Rise of an empire", "threehundred"),
            MovieModel("Guardians of the galaxy", "guardians_of_the_galaxy"),
            MovieModel("Age of uprising", "age_of_uprising"),
            MovieModel("Deadsnow", "deadsnow"),
            MovieModel("Matrix", "matrix"),
            MovieModel("Mummy", "mummy"),
            MovieModel("Hercules", "hercules"),
            MovieModel("Hobbit", "hobbit"),
            MovieModel("Dracula untold", "dracula_untold")
    )

    // Adventure
    var nestedAdventureItems: MutableList<Any> = asList(
            MovieModel("Hercules", "hercules"),
            MovieModel("Mummy", "mummy"),
            MovieModel("Age of uprising", "age_of_uprising"),
            MovieModel("Matrix", "matrix"),
            MovieModel("Hellboy", "hellboy"),
            MovieModel("Dracula untold", "dracula_untold"),
            MovieModel("300 Rise of an empire", "threehundred"),
            MovieModel("Guardians of the galaxy", "guardians_of_the_galaxy"),
            MovieModel("Godzilla king of the mosters", "godzilla_king_of_the_monsters"),
            MovieModel("Godzilla", "godzilla")
    )

    // Sci-Fi
    var nestedSciFiItems: MutableList<Any> = asList(
            MovieModel("Godzilla", "interstellar"),
            MovieModel("Matrix", "matrix"),
            MovieModel("Edge of tomorrow", "edge_of_tomorrow"),
            MovieModel("Ad Astra", "adastra"),
            MovieModel("Avengers endgame", "avengers_endgame1"),
            MovieModel("Avengers endgame", "avengers_endgame2"),
            MovieModel("Avengers endgame", "avengers_endgame3")
    )

    // Animated
    var nestedAnimatedItems: MutableList<Any> = asList(
            MovieModel("The Lion king", "lion_king"),
            MovieModel("Dora", "dora"),
            MovieModel("Frozen 2", "frozen2"),
            MovieModel("Ferdinand", "ferdinand"),
            MovieModel("The Grinch", "grinch"),
            MovieModel("Incredibles 2", "incredibles_2"),
            MovieModel("Abominable", "abominable"),
            MovieModel("How to train your dragon 2", "how_to_train_your_dragon_two"),
            MovieModel("Incredibles 2", "incredibles"),
            MovieModel("Angry birds 2", "angry_birds_movie_two"))

    // Recent viewed items
    var nestedRecentViewedItems = mutableListOf<Any>()
    init {
        nestedRecentViewedItems.addAll(comingSoonItems)
        nestedRecentViewedItems.add(MovieModel("Her", "her"))
        nestedRecentViewedItems.add(MovieModel("Imitation game", "imitation_game"))
        nestedRecentViewedItems.add(MovieModel("Interview", "interview"))
        nestedRecentViewedItems.add(MovieModel("As above so below", "as_above_so_below"))
    }

    private val POSTER_ITEMS = arrayOf(
            "guardians_of_the_galaxy_vol_two",
            "spiderman_far_from_home",
            "the_last_jedi",
            "han_solo",
            "terminator_dark_fate",
            "thor_ragnarok",
            "grinch",
            "logan",
            "alien_covenant_alt",
            "avengers_endgame",
            "first_man",
            "ice_age_collision",
            "john_wick3",
            "predator",
            "rambo4",
            "aladdin",
            "aladdin_alt",
            "joker",
            "murder_mystery",
            "pokemon_detective_pikachu",
            "the_lion_king",
            "toy_story_4",
            "xmen_dark_phoenix",
            "dora")

    val randomPoster: String
        get() = POSTER_ITEMS[Random().nextInt(POSTER_ITEMS.size)]

    private val BANNER_ITEMS = arrayOf(
            "300_rise_of_an_empire",
            "alien_covenant",
            "alien_easter",
            "blaerunner",
            "bond",
            "dark_knight",
            "expendables",
            "finding_dory",
            "interstellar",
            "interstellar_quad",
            "iron_man_alt",
            "jurassic_park_red_big",
            "jurassic_park_redironman",
            "lort",
            "man_of_steel",
            "mi_fallout",
            "new_star_wars",
            "terminator",
            "thor_ragnarok")

    val randomBanner: String
        get() = BANNER_ITEMS[Random().nextInt(BANNER_ITEMS.size)]
}
