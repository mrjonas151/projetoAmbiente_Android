# 🌱 EcoTracker - App de Atividades Sustentáveis

<div align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Retrofit-48B983?style=for-the-badge&logo=square&logoColor=white" />
  <img src="https://img.shields.io/badge/Material%20Design-757575?style=for-the-badge&logo=material-design&logoColor=white" />
</div>

<div align="center">
  <h3>🎯 Monitore seu impacto ambiental de forma inteligente!</h3>
  <p>Uma aplicação Android moderna para registro e acompanhamento de atividades sustentáveis com relatórios gerados por IA.</p>
</div>

---

## ✨ Funcionalidades Principais

### 🏠 **Tela Principal**
- 📱 Interface moderna e intuitiva
- 📋 Lista completa de atividades sustentáveis
- ➕ Acesso rápido para adicionar novas atividades
- 📊 Navegação para estatísticas e relatórios

### 📝 **Gestão de Atividades**
- **Registro de Atividades**: Cadastre suas práticas sustentáveis
- **Detalhes Completos**: Visualize informações detalhadas de cada atividade
- **Edição Flexível**: Modifique atividades já cadastradas
- **Exclusão Segura**: Remova atividades com confirmação

### 📊 **Estatísticas Avançadas**
- 📈 **Gráficos Interativos**: Visualização por pizza das atividades por tipo
- 🔢 **Métricas Importantes**:
  - Total de atividades realizadas
  - Impacto ambiental acumulado
  - Média de impacto diário
- 📱 Cards informativos com dados relevantes

### 🤖 **Relatórios Inteligentes**
- **IA Personalizada**: Relatórios gerados automaticamente por inteligência artificial
- **Análise Completa**: Período de análise e total de atividades
- **Envio por Email**: Compartilhe relatórios diretamente para seu email
- **Interface Amigável**: Loading e feedback visual durante geração

## 🏗️ Arquitetura do Projeto

### 📁 **Estrutura de Pastas**
```
app/src/main/java/com/example/projeto4_mobile/
├── 🎭 activities/          # Telas da aplicação
│   ├── MainActivity.java   # Tela principal
│   ├── RegistroActivity.java # Cadastro de atividades
│   ├── DetalhesActivity.java # Detalhes da atividade
│   ├── EdicaoActivity.java   # Edição de atividades
│   ├── EstatisticasActivity.java # Gráficos e métricas
│   └── RelatorioActivity.java    # Relatórios IA
├── 🔧 adapters/           # Adaptadores RecyclerView
├── 📦 models/             # Modelos de dados
├── 🌐 network/            # Configuração de rede
├── 🛠️ services/           # Serviços API
├── 🎨 ui/                 # Componentes UI
└── ⚙️ utils/             # Utilitários
```

### 🎯 **Tipos de Atividades Suportadas**
- 💧 **Economia de Água**
- 🌳 **Plantio**
- ♻️ **Reutilização**
- 🗂️ **Reciclagem**
- 🚲 **Transporte Sustentável**
- ⚡ **Energia Renovável**

## 🛠️ Tecnologias Utilizadas

### 📱 **Android & Kotlin/Java**
- **SDK Target**: Android 14 (API 35)
- **Minimum SDK**: Android 7.0 (API 24)
- **Build Tools**: Gradle 8.10.0
- **Kotlin**: 2.0.21

### 📚 **Principais Bibliotecas**

#### 🌐 **Networking**
- **Retrofit 2.9.0**: Cliente HTTP type-safe para Android
- **Gson Converter**: Serialização JSON automática

#### 📊 **Gráficos e Visualização**
- **MPAndroidChart v3.1.0**: Biblioteca para gráficos interativos
- Gráficos de pizza personalizáveis
- Animações e interatividade

#### 🎨 **Interface e Design**
- **Material Design 3**: Componentes modernos do Google
- **ConstraintLayout**: Layouts responsivos e flexíveis
- **RecyclerView & CardView**: Listas eficientes e cards elegantes
- **AppCompat**: Compatibilidade com versões antigas

#### 🧪 **Testes**
- **JUnit 4.13.2**: Testes unitários
- **Espresso**: Testes de interface
- **AndroidX Test**: Framework de testes Android

## 🚀 Como Executar o Projeto

### 📋 **Pré-requisitos**
- Android Studio Arctic Fox ou superior
- JDK 11 ou superior
- Android SDK 35
- Dispositivo Android ou Emulador (API 24+)

### 🔧 **Instalação**

1. **Clone o repositório**:
   ```bash
   git clone github.com/mrjonas151/projetoAmbiente_Android
   cd projetoAmbiente_Android
   ```

2. **Abra no Android Studio**:
   - File → Open → Selecione a pasta do projeto

3. **Sincronize as dependências**:
   - Aguarde o Gradle sincronizar automaticamente
   - Ou execute: `./gradlew build`

4. **Configure o dispositivo**:
   - Conecte um dispositivo Android ou
   - Configure um emulador no AVD Manager

5. **Execute o projeto**:
   - Clique em "Run" ou pressione `Shift + F10`

### ⚙️ **Configuração da API**
O projeto utiliza uma API REST para persistência de dados. Certifique-se de:
- Configurar a URL base da API no `ApiClient`
- Verificar conectividade com internet
- Validar endpoints da API

## 📱 Screenshots e Fluxo de Uso

### 🏠 **Tela Principal**
- Lista todas as atividades sustentáveis cadastradas
- Botões de navegação para diferentes funcionalidades
- Interface clean e organizada

### ➕ **Cadastro de Atividade**
1. Selecione o tipo de atividade
2. Descreva a atividade realizada
3. Informe o impacto ambiental e unidade
4. Salve ou cancele a operação

### 📊 **Estatísticas**
- Cards com métricas importantes
- Gráfico de pizza interativo
- Distribuição por tipo de atividade

### 🤖 **Relatórios IA**
1. Clique em "Gerar Relatório IA"
2. Aguarde o processamento
3. Visualize o relatório personalizado
4. Envie por email se necessário

## 🔄 API Endpoints

O aplicativo consome uma API REST com os seguintes endpoints:

```
GET    /atividades              # Lista todas as atividades
POST   /atividades              # Cria nova atividade
GET    /atividades/{id}         # Busca atividade por ID
PUT    /atividades/{id}         # Atualiza atividade
DELETE /atividades/{id}         # Remove atividade
GET    /estatisticas            # Busca estatísticas
GET    /relatorio-personalizado # Gera relatório IA
POST   /enviar-email           # Envia relatório por email
```

## 🎨 Design e UX

### 🌈 **Paleta de Cores**
- **Verde**: Ações positivas e sustentabilidade
- **Azul**: Navegação e informações
- **Roxo**: Funcionalidades de IA
- **Cinza**: Textos secundários e cancelamentos

### 🎯 **Princípios de Design**
- **Material Design 3**: Seguindo as diretrizes do Google
- **Acessibilidade**: Contrastes adequados e textos legíveis
- **Responsividade**: Adaptação a diferentes tamanhos de tela
- **Feedback Visual**: Loading, toasts e confirmações

## 🤝 Contribuindo

1. **Fork** o projeto
2. Crie uma **branch** para sua feature (`git checkout -b feature/AmazingFeature`)
3. **Commit** suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. **Push** para a branch (`git push origin feature/AmazingFeature`)
5. Abra um **Pull Request**

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Desenvolvido por

**Jonas Tomaz**

---

<div align="center">
  <h3>🌍 Juntos por um planeta mais sustentável! 🌱</h3>
  <p><em>Cada pequena ação conta para um futuro melhor.</em></p>
</div>
