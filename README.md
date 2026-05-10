# FindApart

FindApart is a native Android mobile application designed for browsing, filtering, and locating apartment listings with a local persistence layer for favorites.

## Core Features
1. **Multi-Criteria Search (Home):**
   - Users can filter listings by City, Building Type, Square Meters, Room Count, Floor, Distance from Centre, Ownership Type, and Price Range.
   - Dynamic dropdowns are populated from `strings.xml` and handled via `DropdownMapping`.
   - Validates search results count before navigating to the result list.

2. **Smart Listing & Sorting (List):**
   - Displays search results in a detailed, expandable `RecyclerView`.
   - **Interactive Details:** Tapping a card expands it to reveal high-resolution data (15+ proximity metrics, material info, condition).
   - **On-the-fly Sorting:** A Floating Action Button (FAB) menu allows sorting results by Price, Area, Rooms, Floor, or Centre Distance (Ascending/Descending).
   - **Map Redirection:** Each listing has a direct "Show on Map" button that navigates to the property's specific coordinates.

3. **Integrated Map View:**
   - Powered by Google Maps SDK with custom styling (`map_style.json`).
   - **Saved Markers:** Automatically displays all favorited apartments as Blue markers.
   - **Target Focus:** When navigating from a specific listing, focuses on that property with a Cyan marker and high-zoom animation.

4. **Favorites System (Saved):**
   - Persistent "Like" button on cards managed via a separate local database table.
   - Separate "Saved" tab to browse your shortlisted properties.

## Architecture & Technical Stack
- **Tech:** Kotlin 1.9+, Android SDK (Min 26, Target 35).
- **Navigation:** Single-Activity Architecture (`MainActivity`) using Jetpack Navigation Component.
- **Database (The "Raw" Engine):**
  - Uses standard **SQLiteDatabase** (no ORM/Room) for maximum control.
  - **Auto-Provisioning:** On first run, the app extracts and executes a raw SQL dump from `assets/apartments.sql` to populate `apartments.db`.
- **UI:** XML Layouts with **ViewBinding**. Features `CollapsingToolbarLayout` in the main activity for a modern scrolling experience.
- **Dependency Management:** Modern Gradle configuration with Kotlin DSL and Version Catalogs.

## Data Model Highlights
The `Apartment` entity is rich in data, tracking:
- **Distances:** Proximities to Schools, Clinics, Post Offices, Kindergartens, Restaurants, Colleges, and Pharmacies.
- **Amenities:** Parking, Balcony, Elevator, Security, Storage Room.
- **Attributes:** City, Type (Block, Apartment, Tenement), Floor/Total Floors, Build Year, Ownership, Material, Condition.

## Getting Started
1. **Requirements:** Android Studio Koala+, Gradle 8.8.0+.
2. **API Keys:** Provide a valid Google Maps API Key in `res/values/secrets.xml` under `<string name="google_maps_key">`.
3. **Data Initializing:** The app will automatically initialize the database from the SQL asset on the first launch.
