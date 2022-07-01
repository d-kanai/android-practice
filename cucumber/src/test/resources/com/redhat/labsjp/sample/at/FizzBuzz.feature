Feature: FizzBuzz動作確認

    Scenario Outline: 入力として３と５の倍数を入力してボタンをクリックするとFizzBuzzという文字列が表示される
        Given FizzBuzzアプリが起動されている
        When 入力フィールドに"<inputStr>"を入力する
        And ボタンをクリックする
        Then ボタンの下に"FizzBuzz"という文字列が表示される
        Examples:
            | inputStr |
            | 15       |
            | 30       |
            | 90       |

    Scenario Outline: 入力として３の倍数を入力してボタンをクリックするとFizzという文字列が表示される
        Given FizzBuzzアプリが起動されている
        When 入力フィールドに"<inputStr>"を入力する
        And ボタンをクリックする
        Then ボタンの下に"Fizz"という文字列が表示される
        Examples:
            | inputStr |
            | 3        |
            | 6        |
            | 99       |

    Scenario Outline: 入力として５の倍数を入力してボタンをクリックするとFizzという文字列が表示される
        Given FizzBuzzアプリが起動されている
        When 入力フィールドに"<inputStr>"を入力する
        And ボタンをクリックする
        Then ボタンの下に"Buzz"という文字列が表示される
        Examples:
            | inputStr |
            | 5        |
            | 10       |
            | 100      |

    Scenario Outline: 入力として３の倍数でも５の倍数でもない数字を入力してボタンをクリックするとAという文字列が表示される
        Given FizzBuzzアプリが起動されている
        When 入力フィールドに"<inputStr>"を入力する
        And ボタンをクリックする
        Then ボタンの下に"<resultStr>"という文字列が表示される
        Examples:
            | inputStr | resultStr |
            | 1        | 1  |
            | 2        | 2  |
            | 4        | 4  |
            | 98       | 98 |

    Scenario: 入力として数字以外を入力してボタンをクリックするとErrorという文字列が表示される
        Given FizzBuzzアプリが起動されている
        When 入力フィールドに"AAAAA"を入力する
        And ボタンをクリックする
        Then ボタンの下に"Error!"という文字列が表示される
