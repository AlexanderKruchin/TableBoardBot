import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Buttons {
    AddGame("Добавить игру")
    ,DeleteGame("Удалить игру")
    ,StartVote("Начать голосование")
    ,RefreshGameList("Очистить список");
    private final String id;

}
