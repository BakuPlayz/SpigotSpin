<h1 align="center"> SpigotSpin </h1> <br>
<p align="center">
    <img alt="SpigotSpin" title="SpigotSpin" src="assets/spigot_spin_logo.png">
</p>

<p align="center">
  Seamless Minecraft menu-creation in your pocket, with react-like updatability and pagination support built-in.
</p>

> [!Note]
> This library is still in its Beta days, however feel free to use it and send in feature requests if
> you feel like something is missing or needing more attention.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Setup](#setup)
- [Examples](#examples)
    - [Plain menus](#plain-menus)
    - [Stateful menus](#stateful-menus)
    - [Paginated menus](#paginated-menus)
    - [Shared menus](#shared-menus)
- [Build Process](#build-process)

## Introduction

SpigotSpin is a react-like menu library meant for easier sizing, pagination and stateful menus. It does this while still
being compatible with older as well as newer Minecraft versions with big thanks
to [XSeries](https://github.com/CryptoMorin/XSeries) and all its
amazing [contributors and authors](https://github.com/CryptoMorin/XSeries/graphs/contributors).

## Features

An overviewing picture of what SpigotSpin is capable of currently with its menus:

* **Smartly sized menus**: Automatically adjusts the size based on item placements.
* **Simple static menus**: Handle simple menus without states to serve non-updatable information.
* **Seamless stateful menus**: Handle state updates with ease using a React-like structure.
* **Straightforward paginated menus**: Easily add pagination to a menu without many adjustments.
* **Smooth shared menus**: Allow multiple players to open and interact with the same menu.

## Setup

First begin by adding SpigotSpin into your maven dependencies, which simply can be done by adding the following into
your pom.xml and replacing the `version` with the current version:

```xml

<dependency>
    <groupId>dev.bakuplayz</groupId>
    <artifactId>spigotspin</artifactId>
    <version>version</version>
</dependency>
```

Thereafter, before you can start programming using SpigotSpin you must register it by creating a new instance in your
main class, the one which extends JavaPlugin in this case it's "YourPlugin". This also registers SpigotSpins own menu
listeners which handles all the clicking and soon-TM also dragging events.

```java
public class YourPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // ... Do something
        new SpigotSpin(this);
        // ... Do something
    }

}
```

## Examples

Are you ready to get speeding with your menus, then these examples will help you get going. Observe! these are written
in a top-down format meaning that the beginning with [Plain menus](#plain-menus) will also explain e.g. how to create
items and how to open your created menu.

> Note these examples can also be seen as example code directly under
> the
> [dev.bakuplayz.spigotspin.examples](https://github.com/BakuPlayz/SpigotSpin/tree/main/src/main/java/dev/bakuplayz/spigotspin/examples)
> directory, for people who just want to dive straight into SpigotSpin

### Plain menus

Plain menus are the most simple types of menus, it can have plain items, clickable items and draggable items. Meaning
that implementing such a menu is very simple as to be shown below. However, we'll begin creating a plain menu by
extending the `AbstractDynamicPlainMenu` and providing it with its title as a super parameter,
e.g. `super("Plain")` would make the title be "Plain".

<details>
  <summary><i>Click to view or hide</i></summary>
  <br>

Furthermore, as can also be seen below we must declare an `setItems()` method where we set our inventory items
which is placed right before the player opens the menu `ExamplePlain.open(Player player)`. In this case we have a
clickable item named `ExampleItem`, which executes `(item) -> LOGGER.info(String.valueOf(item.getPosition()))`
when clicked on by the player. Which simply just logs the position of the item in the inventory, which would be 9.
<br>
<br>

```java
public final class ExamplePlain extends AbstractDynamicPlainMenu {

    public static final Logger LOGGER = Logger.getLogger("ExamplePlain");


    public ExamplePlain() {
        super("Plain");
    }


    @Override
    public void setItems() {
        setItem(9, new ExampleItem(), (item) -> LOGGER.info(String.valueOf(item.getPosition())));
    }

}

```

#### Creating an item

As can be seen above we have already added a new item to our `ExamplePlain` menu by calling the `setItem()`
method in our `setItems()` method. However, we've not covered how these items are to be created. The following shows
us creating our `ExampleItem` with a name of "Test" and a multi-version supported material Anvil, meaning it would
show up as an Anvil item with that same name. In addition to this we also declare it as an ClickableItem, which as
explained above make us able to perform click-specific functionality when a player clicks it.
<br>

```java
import dev.bakuplayz.spigotspin.abstraction.menu.items.ClickableItem;

public final class ExampleItem extends ClickableItem {

    public ExampleItem() {
        setName("Test");
        setMaterial(XMaterial.ANVIL);
    }

}
```

Please note that only `XMaterial` are supported as materials inside SpigotSpin, due to the nature of SpigotSpin
supporting multiple versions (1.8 -> 1.20.6) equally.

#### Opening the menu

Now you can simply make a command, block listener or whatever call the `ExamplePlain.open(player)` in-order to make
the given player open your newly created menu. Are you ready for some cool 😎 state menus? Then check the next section.

</details>

### Stateful menus

Stateful menus are one of the most useful types of menus, as they allow for state updatable lists, sets, integers and
pretty much anything you try to throw at it. However, being this powerful comes with a cost in sense of more classes to
handle state and updating state. When creating a state menu you will therefore, except for the menu class, also require
a `State`, `StateFlag` and `StateHandler` to contain, point to and update state respectively.

<details>
  <summary><i>Click to view or hide</i></summary>
  <br>

We begin by defining our `State` for which will contain or hold our state, as can be seen above, which in this case
is our count. In addition to this count we also make it generate getters and setters for all the fields by defining it
on the class using [Lombok](https://projectlombok.org/). This is done since we don't want to directly affect our count
inside the class, but rather update it using our setter as we'll see soon.
<br>
<br>

```java
import dev.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuState;

@Getter
@Setter
public final class ExampleState implements MenuState {

    private int count;

}
```

#### Creating a StateFlag

Secondly, we create this enum-like class, `StateFlag` where we define flags for our states, so in this case we only
create one for `count` by giving it the name `COUNT`. Where we also define this `COUNT` to one. However,
note that this value doesn't really matter as long as they are unique.
<br>

```java
public final class ExampleStateFlag {

    public static final int COUNT = 1;

}
```

An example of two or more of these state flags would then be as follows:
<br>

```java
public final class AnotherExampleStateFlag {

    public static final int COUNT = 1;

    public static final int SECOND_COUNT = 2;

    // ... And so on

}
```

#### Creating an StateHandler

Continuing on, we need an `StateHandler` which will update our state so that our items will get notified when a
state changed and so on. The idea of this state handler is quite simple, it basically begins in the constructor where
you define a `MenuStateObserver` for your given state, in this case `ExampleState`, as well as creating a new instance
for the state `new ExampleState()` or rather creating an initial state and sending that through to the parent.

Moreover, you define the `onUpdateState` method, for which takes in a partial state `P` of our full
state `ExampleState`, meaning that if we update our count we'll then receive this as a partial state `P` into
our `onUpdateState` method. Then when given this partial state we match on the flag, so in this case we would match on
the `ExampleStateeFlag.COUNT` as the count state received an update that we then want to apply using
the `state.setCount(infer(partialState))`.

In addition to these two steps, you should also define a helper function inside your `StateHandler` which makes updating
the state more readable when working with the menu. This can be done as seen in the `incrementCounter` method which as
the name suggests increments the counter, by first inferring the type from `state.getCount()`, providing an update
function `(counter) -> counter + 1` which provides the count state as a parameter and applies the function on it, and
finally the flag is sent to tell all state items that an update to the counter just happened.

<details>
  <summary><i>Click to view or hide a note about the infer method used in the code below</i></summary>
<br>
You might ask why the partial state is wrapped inside an `infer` method, and that's just to simplify your development
and making it easier to maybe switch types later since it will 'infer' it rather than having it be
e.g. `state.setCount((Integer) partialState)`. Which would then provide you with plenty of errors if you made count to
be a set all of a sudden.
</details>
<br>

```java
import dev.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuStateHandler;
import dev.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuStateObserver;

import org.jetbrains.annotations.NotNull;

public final class ExampleStateHandler extends MenuStateHandler<ExampleState, MenuStateObserver<ExampleState>> {

    public ExampleStateHandler(@NotNull MenuStateObserver<ExampleState> observer) {
        super(observer, new ExampleState());
    }


    public void incrementCounter() {
        updateState(state.getCount(), (counter) -> counter + 1, ExampleStateFlag.COUNT);
    }


    @Override
    public <P> ExampleState onUpdateState(@NotNull P partialState, int flag) {
        if (flag == ExampleStateFlag.COUNT) {
            state.setCount(infer(partialState));
        }

        // ... And so on

        return state;
    }

}
```

#### Creating an StateItem

Furthermore, you might also want to create a state item. This can be done by currently extending either `StateItem`
or `ClickableStateItem`. You then construct it by using the different setters provided such as `setName` which sets the
display name of the item or `setMaterial` which sets the material of the item. However, once again note that only
XMaterial is allowed as material as SpigotSpin tries to support legacy to newer versions at the same time.

However, when working with state items you also get the `update` method which will receive updates whenever the given
state or states we later provide in our menu occur. Meaning that if our count updates then we'll receive the state of
the newly updated count which we then in this case use to change the color to `Count: <number>`.
<br>

```java
import dev.bakuplayz.spigotspin.abstraction.menu.items.state.ClickableStateItem;

import org.jetbrains.annotations.NotNull;

public final class ExampleItem extends ClickableStateItem<ExampleState> {

    public ExampleItem(@NotNull String name) {
        setName(String.format("&e%s", name));
        setMaterial(XMaterial.ANVIL);
    }


    @Override
    public void update(@NotNull ExampleState state, int flag) {
        setLore(String.format("Count: %d", state.getCount()));
    }
}

```

#### Creating the StateMenu

Lastly, we create the menu that depend on the `State`, `StateFlag` and `StateHandler` and uses `StateItem` which we
explained and created above. Here we once again set our items using the `setItems()` method, however when we use
the `setItem()` method you might observe that we also include a lambda-function with our item as an argument as well as
a flag at the end. This is due to the fact that when we provide a `ClickItem` we must also provide an `ClickableAction`
which is performed once a player clicks on it as explained above under [creating an item](#creating-an-item). And then
the flag or flags are given to make the `StateItem` listen for changes to these states via these flags.
<br>

```java
import dev.bakuplayz.spigotspin.abstraction.menu.menus.AbstractDynamicStateMenu;

public final class ExampleStateMenu extends AbstractDynamicStateMenu<ExampleState> {

    private final ExampleStateHandler stateHandler;


    public ExampleStateMenu() {
        super("State example");
        this.stateHandler = new ExampleStateHandler(this);
    }


    @Override
    public void setItems() {
        setItem(9, new ExampleItem("Test item"), (item) -> stateHandler.incrementCounter(), ExampleStateFlag.COUNT);
    }

}
```

</details>

### Paginated menus

Paginated menus are another type of menus that is important when creating menus, as sometimes you just want to have a
menu to e.g. see all of your sat homes or perhaps toggle a plugin for a specific player or so on. Please note that this
menu type is extending the `AbstractDynamicStateMenu` as showing the current page, moving to the next or previous page
is all state actions, so please read about stateful menus if you haven't already.

<details>
  <summary><i>Click to view or hide</i></summary>
<br>

This time we begin by creating the menu immediately instead of creating all the between steps that are shown under
the [Stateful menus](#stateful-menus) as it to this point is exactly the same. So if you want to configure your state
further or learn more about how these paginated menus work then head on over to there.
<br>
<br>

The only difference between the `AbstractDynamicStateMenu` and `AbstractDynamicPaginatedMenu` is the `getItemsAmount()`
and `loadItem()` methods, which define how many items we have in our pagination and how to render these respectively.
However, there are more things you can change but are not required to such as `isFramePosition()` to define how the
frame around the items should be rendered which directly changes the amount of items there shall be on each page.
Further, you can also define your own `getPreviousItem()`, `getCurrentItem()` and `getNextItem()` which are the
indicators at the bottom of each page to head back, see the current or go to the next page respectively.
<br>
<br>

```java
import dev.bakuplayz.spigotspin.abstraction.menu.menus.AbstractDynamicPaginatedMenu;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class ExamplePaginated extends AbstractDynamicPaginatedMenu<ExampleState, ExampleStateHandler> {


    private final List<String> items = Arrays.asList("Hello", "World!");


    public ExamplePaginated() {
        super("Paginated example");
    }


    @Override
    public int getItemsAmount() {
        return items.size();
    }


    @NotNull
    @Override
    public Item loadItem(int itemPosition, int inventoryPosition, int page, int displayPage) {
        return new ExampleItem(items.get(itemPosition * displayPage));
    }

}
```

</details>

### Shared menus

Shared menus are yet another type of menus that may be helpful when showing the very same menu to multiple players at
once, e.g. you might want to show an auction page which multiple players can view the same state on at the same time and
then make the load item call an API with the items that is auctioning right now, etc.

Please note that this menu type is extending the `AbstractDynamicStateMenu` as it by default allows state updates to be
viewed by and affected by multiple players at once.

<details>
  <summary><i>Click to view or hide</i></summary>
<br>

This time we will once again begin by creating the menu immediately instead of creating all the between steps that are
shown under the [Stateful menus](#stateful-menus) as it to this point is exactly the same. So if you want to configure
your state further or learn more about how these paginated menus work then head on over to there.  
<br><br>
The only difference between the `AbstractDynamicStateMenu` and `AbstractDynamicSharedMenu` is that the shared menu
doesn't allow calls to the `open()` method, instead it uses the `join(identifier)` method which takes in an identifier that is then joinable by multiple players using the very same identifier. Changing this identifier would allow for another subtype of menus that share state for multiple players. 
<br><br>

> However, note that when the last player has left the menu the state and everything manipulated inside of it will be
> thrown away and removed by SpigotSpin. So don't be mistaken by this behaviour if you are meet by this during
> development. Also note, that this is subject to change later so that a menu can be persisted even though all players
> leave and some other modifications are also missing such as limiting which player is allowed to click at a specific
> item and so on as well.

If you want to see the specific example code for this head to the directory for this example, which is found under
the [shared directory](https://github.com/BakuPlayz/SpigotSpin/tree/main/src/main/java/dev/bakuplayz/spigotspin/examples/shared).

</details>

## Build Process

In order to build and run SpigotSpin on your own machine you must first have java and maven installed.

* Clone the repository, `git clone https://github.com/BakuPlayz/SpigotSpin.git`
* Head into the directory, `cd SpigotSpin`
* Compile the project, `mvn package`

The compiled jar files should now be found under the target folder. 
