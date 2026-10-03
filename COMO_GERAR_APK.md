# Trading PRO — gerar APK instalável

Este projeto contém o aplicativo Android, ícone, tela de abertura e painel web local.

## Forma mais fácil

1. Crie um repositório no GitHub.
2. Envie todos os arquivos desta pasta para o repositório.
3. Abra **Actions**.
4. Selecione **Build Trading PRO APK**.
5. Clique em **Run workflow**.
6. Quando terminar, abra a execução concluída.
7. Em **Artifacts**, baixe **Trading-PRO-APK**.
8. Dentro do ZIP estará `app-debug.apk`.
9. Envie o APK para o celular e toque nele para instalar.

O APK é de teste (debug) e serve para instalação manual no Android.

## Se o Android bloquear a instalação

Autorize a instalação de aplicativos da fonte usada para abrir o APK (por exemplo, Chrome ou Arquivos), quando o Android solicitar.

## Por que o APK não veio pronto aqui?

O ambiente desta conversa não possui Android SDK/Gradle instalados. Por isso, o projeto está pronto para compilação, mas a compilação do APK precisa ser feita em Android Studio ou em um serviço de CI como o GitHub Actions.
